package com.springboottemplate.domain.common.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.infrastructure.cache.redis.RedisCacheTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 缓存中心  提供全局访问点 如果是领域类的缓存  可以自己新建一个直接放在CacheCenter   不用放在infrastructure包里的GuavaCacheService 或者RedisCacheService
 *
 * @author valarchie
 */
@Component
public class CacheCenter {

    public static RedisCacheTemplate<SysUserEntity> userCache;

    @PostConstruct
    public void init() {
        RedisCache redisCache = SpringUtil.getBean(RedisCache.class);
        userCache = redisCache.userCache;
    }
}
