package com.springboottemplate.admin.customize.config;


import cn.hutool.json.JSONUtil;
import com.springboottemplate.admin.customize.service.login.TokenService;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode.Client;
import com.springboottemplate.common.utils.ServletHolderUtil;
import com.springboottemplate.infrastructure.user.AuthenticationUtils;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        try {
            SystemLoginUser loginUser = tokenService.getLoginUser(request);
            if (loginUser != null && AuthenticationUtils.getAuthentication() == null) {
                tokenService.refreshToken(loginUser);
                // 如果没有将当前登录用户放入到上下文中的话，会认定用户未授权，返回用户未登陆的错误
                putCurrentLoginUserIntoContext(request, loginUser);

                log.debug("request process in jwt token filter. get login user id: {}", loginUser.getUserId());
            }
        } catch (Exception e) {
            fallback(e, request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    private void fallback(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        ApiException apiException;
//        todo  没找到具体导致SpringSecurity为什么出现的异常会走/error路由的原因 但这样可以保证ApiException可以正常处理
        // 检查 exception 是否为 ApiException
        if (exception instanceof ApiException) {
            apiException = (ApiException) exception;
        } else {
            // 使用默认的 ApiException 处理未知异常
            apiException = new ApiException(Client.COMMON_NO_AUTHORIZATION, request.getRequestURI());
        }
        ResponseDTO<Object> responseDTO = ResponseDTO.fail(apiException);
        ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(responseDTO));

    }

    private void putCurrentLoginUserIntoContext(HttpServletRequest request, SystemLoginUser loginUser) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser,
            null, loginUser.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
