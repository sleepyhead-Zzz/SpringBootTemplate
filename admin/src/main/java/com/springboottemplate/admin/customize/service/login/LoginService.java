package com.springboottemplate.admin.customize.service.login;

import com.springboottemplate.admin.customize.service.login.command.LoginCommand;
import com.springboottemplate.domain.common.cache.RedisCacheService;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final TokenService tokenService;

    private final RedisCacheService redisCache;

    /**
     * 登录验证
     *
     * @param loginCommand 登录参数
     * @return 结果
     */
    public String login(LoginCommand loginCommand) {
        SystemLoginUser user = new SystemLoginUser();
        user.setUsername("123");
        // 生成token
        return tokenService.createTokenAndPutUserInCache(user);
    }

}
