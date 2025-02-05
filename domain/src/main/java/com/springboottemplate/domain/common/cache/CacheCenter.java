package com.springboottemplate.domain.common.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.springboottemplate.domain.system.dept.db.SysDeptEntity;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.infrastructure.cache.guava.AbstractGuavaCacheTemplate;
import com.springboottemplate.infrastructure.cache.redis.RedisCacheTemplate;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 缓存中心  提供全局访问点 如果是领域类的缓存  可以自己新建一个直接放在CacheCenter   不用放在infrastructure包里的GuavaCacheService 或者RedisCacheService
 *
 * @author Sleepyhead
 */
@Component
public class CacheCenter {

    public static AbstractGuavaCacheTemplate<SysDeptEntity> deptCache;

    public static RedisCacheTemplate<SystemLoginUser> loginUserCache;

    public static RedisCacheTemplate<SysUserEntity> userCache;

    public static RedisCacheTemplate<SysRoleEntity> roleCache;

    public static RedisCacheTemplate<SysPostEntity> postCache;

    @PostConstruct
    public void init() {
        GuavaCacheService guavaCache = SpringUtil.getBean(GuavaCacheService.class);
        RedisCache redisCache = SpringUtil.getBean(RedisCache.class);

        deptCache = guavaCache.deptCache;

        loginUserCache = redisCache.loginUserCache;
        userCache = redisCache.userCache;
        roleCache = redisCache.roleCache;
        postCache = redisCache.postCache;
    }
}
