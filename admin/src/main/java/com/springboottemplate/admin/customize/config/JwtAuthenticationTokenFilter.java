package com.springboottemplate.admin.customize.config;


import com.springboottemplate.admin.customize.service.login.TokenService;
import com.springboottemplate.infrastructure.user.AuthenticationUtils;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * token过滤器 验证token有效性 继承OncePerRequestFilter类的话  可以确保只执行filter一次， 避免执行多次
 *
 * @author valarchie
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull FilterChain chain)
        throws ServletException, IOException {
        SystemLoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null && AuthenticationUtils.getAuthentication() == null) {
            tokenService.refreshToken(loginUser);
            // 如果没有将当前登录用户放入到上下文中的话，会认定用户未授权，返回用户未登陆的错误
            putCurrentLoginUserIntoContext(request, loginUser);

            log.debug("request process in jwt token filter. get login user id: {}", loginUser.getUserId());
        }
        chain.doFilter(request, response);
    }


    private void putCurrentLoginUserIntoContext(HttpServletRequest request, SystemLoginUser loginUser) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser,
            null, loginUser.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
