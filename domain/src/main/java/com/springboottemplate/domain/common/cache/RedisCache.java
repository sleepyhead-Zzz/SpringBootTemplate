package com.springboottemplate.domain.common.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.domain.system.user.db.SysUserService;
import com.springboottemplate.infrastructure.cache.RedisUtil;
import com.springboottemplate.infrastructure.cache.redis.CacheKeyEnum;
import com.springboottemplate.infrastructure.cache.redis.RedisCacheTemplate;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class RedisCache {

    private final RedisUtil redisUtil;

    public RedisCacheTemplate<SysUserEntity> userCache;

    public RedisCacheTemplate<SystemLoginUser> loginUserCache;


    @PostConstruct
    public void init() {
        loginUserCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.LOGIN_USER_KEY);

        userCache = new RedisCacheTemplate<SysUserEntity>(redisUtil, CacheKeyEnum.USER_ENTITY_KEY) {
            @Override
            public SysUserEntity getObjectFromDb(Object id) {
                SysUserService userService = SpringUtil.getBean(SysUserService.class);
                return userService.getById((Serializable) id);
            }
        };
    }
}
