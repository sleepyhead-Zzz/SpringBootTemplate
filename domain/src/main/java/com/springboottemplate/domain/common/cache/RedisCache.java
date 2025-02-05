package com.springboottemplate.domain.common.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import com.springboottemplate.domain.system.post.db.SysPostService;
import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import com.springboottemplate.domain.system.role.db.SysRoleService;
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
 * @author Sleepyhead
 */
@Component
@RequiredArgsConstructor
public class RedisCache {

    private final RedisUtil redisUtil;

    public RedisCacheTemplate<SysUserEntity> userCache;

    public RedisCacheTemplate<SystemLoginUser> loginUserCache;

    public RedisCacheTemplate<SysRoleEntity> roleCache;

    public RedisCacheTemplate<SysPostEntity> postCache;

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
        roleCache = new RedisCacheTemplate<SysRoleEntity>(redisUtil, CacheKeyEnum.ROLE_ENTITY_KEY) {
            @Override
            public SysRoleEntity getObjectFromDb(Object id) {
                SysRoleService roleService = SpringUtil.getBean(SysRoleService.class);
                return roleService.getById((Serializable) id);
            }
        };

        postCache = new RedisCacheTemplate<SysPostEntity>(redisUtil, CacheKeyEnum.POST_ENTITY_KEY) {
            @Override
            public SysPostEntity getObjectFromDb(Object id) {
                SysPostService postService = SpringUtil.getBean(SysPostService.class);
                return postService.getById((Serializable) id);
            }

        };
    }
}
