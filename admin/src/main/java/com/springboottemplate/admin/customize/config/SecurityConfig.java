package com.springboottemplate.admin.customize.config;

import cn.hutool.json.JSONUtil;
import com.springboottemplate.admin.customize.service.login.TokenService;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode.Client;
import com.springboottemplate.common.utils.ServletHolderUtil;
import com.springboottemplate.domain.common.cache.RedisCacheService;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
 * @author valarchie
 * @see UserDetailsServiceImpl#loadUserByUsername  用于登录流程通过用户名加载用户
 * @see this#unauthorizedHandler()  用于用户未授权或登录失败处理
 * @see this#logOutSuccessHandler 用于退出登录成功后的逻辑
 * @see JwtAuthenticationTokenFilter#doFilter token的校验和刷新
 * @see LoginService#login 登录逻辑
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenService tokenService;

    private final RedisCacheService redisCache;

    /**
     * token认证过滤器
     */
    private final JwtAuthenticationTokenFilter jwtTokenFilter;

    private final UserDetailsService userDetailsService;


    /**
     * 登录异常处理类 用户未登陆的话  在这个Bean中处理
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedHandler() {
        return (request, response, exception) -> {
            ResponseDTO<Object> responseDTO = ResponseDTO.fail(
                new ApiException(Client.COMMON_NO_AUTHORIZATION, request.getRequestURI())
            );
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
//                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(
//                    userName, LoginStatusEnum.LOGOUT, LoginStatusEnum.LOGOUT.description()));
            }
            ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(ResponseDTO.ok()));
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // CSRF禁用，因为不使用session
            .csrf(AbstractHttpConfigurer::disable)
            .cors((cors) -> cors.configurationSource(configurationSource()))
            // 认证失败处理类
            .exceptionHandling((exceptionHandling) -> exceptionHandling
                .authenticationEntryPoint(unauthorizedHandler()))
            // 基于token，所以不需要session
            .sessionManagement(
                (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 过滤请求
            .authorizeRequests((authorizeRequests) ->
                // 这里过滤一些 不需要token的接口地址
                authorizeRequests
                    .requestMatchers("/api/v1/test/getTestInfo").permitAll()
                    .requestMatchers("/v3/**", "/profile/**", "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**", "/swagger-ui/**", "/v2/**", "/favicon.ico", "/webjars/springfox-swagger-ui/**",
                        "/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
                        "/swagger-resources/configuration/ui",
                        "/doc.html",
                        "/test/**",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                    .requestMatchers("/api/v1/user/login", "/api/v1/user/getImageCode").permitAll()
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