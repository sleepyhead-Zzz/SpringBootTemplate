package com.springboottemplate.admin.customize.config;

import cn.hutool.json.JSONUtil;
import com.springboottemplate.admin.customize.async.AsyncTaskFactory;
import com.springboottemplate.admin.customize.service.login.TokenService;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.common.enums.common.LoginStatusEnum;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode.Client;
import com.springboottemplate.common.utils.ServletHolderUtil;
import com.springboottemplate.domain.common.cache.RedisCache;
import com.springboottemplate.infrastructure.thread.ThreadPoolManager;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 主要配置登录流程逻辑涉及以下几个类
 *
 * @author Sleepyhead
 * @see this#unauthorizedHandler()  用于用户未授权或登录失败处理
 * @see this#logOutSuccessHandler 用于退出登录成功后的逻辑
 * @see JwtAuthenticationTokenFilter#doFilter token的校验和刷新
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenService tokenService;

    private final RedisCache redisCache;

    /**
     * token认证过滤器
     */
    private final JwtAuthenticationTokenFilter jwtTokenFilter;

    private final UserDetailsService userDetailsService;


    @Bean
    public AuthenticationManager authenticationManager(
        UserDetailsService userDetailsService,
        BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录异常处理类 用户未登陆的话  在这个Bean中处理
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedHandler() {
        return (request, response, exception) -> {
            ApiException apiException;

            // 检查 exception 的 cause 是否为 ApiException
            if (exception.getCause() instanceof ApiException) {
                apiException = (ApiException) exception.getCause();
            } else {
                // 使用默认的 ApiException 处理未知异常
                apiException = new ApiException(Client.COMMON_NO_AUTHORIZATION, request.getRequestURI());
            }

            ResponseDTO<Object> responseDTO = ResponseDTO.fail(apiException);
            ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(responseDTO));
        };
    }


    /**
     * 退出成功处理类 返回成功 在SecurityConfig类当中 定义了/logout 路径对应处理逻辑
     */
    @Bean
    public LogoutSuccessHandler logOutSuccessHandler() {
        return (request, response, authentication) -> {
            SystemLoginUser loginUser = tokenService.getLoginUser(request);
            if (loginUser != null) {
                String userName = loginUser.getUsername();
                // 删除用户缓存记录
                redisCache.loginUserCache.delete(loginUser.getCachedKey());
                // 记录用户退出日志
                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(
                    userName, LoginStatusEnum.LOGOUT, LoginStatusEnum.LOGOUT.description()));
            }
            ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(ResponseDTO.ok()));
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // CSRF禁用，因为不使用session
            .csrf(AbstractHttpConfigurer::disable)
            // 允许跨域请求
            .cors((cors) -> cors.configurationSource(configurationSource()))
            // 配置认证失败处理类

            .exceptionHandling((exceptionHandling) ->
                exceptionHandling.authenticationEntryPoint(unauthorizedHandler()))
            // 基于token，所以不需要session
            .sessionManagement(
                (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 过滤请求
            .authorizeHttpRequests((authorizeRequests) ->
                // 这里过滤一些 不需要token的接口地址
                authorizeRequests
                    .requestMatchers("/login", "/register", "/error").permitAll()
                    .requestMatchers("/v3/**", "/profile/**", "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/doc.html", "/webjars/**", "/swagger-ui/**", "/favicon.ico",
                        "/test/**").permitAll()
                    .anyRequest().authenticated()
            )
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logOutSuccessHandler()))
            // 禁用 X-Frame-Options 响应头。下面是具体解释：
            // X-Frame-Options 是一个 HTTP 响应头，用于防止网页被嵌入到其他网页的 <frame>、<iframe> 或 <object> 标签中，从而可以减少点击劫持攻击的风险
            .headers((headers) -> headers.frameOptions((FrameOptionsConfig::disable)))
            .headers((headers) -> headers.frameOptions((FrameOptionsConfig::sameOrigin)));
        // 添加JWT filter   需要一开始就通过token识别出登录用户 并放到上下文中   所以jwtFilter需要放前面
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
