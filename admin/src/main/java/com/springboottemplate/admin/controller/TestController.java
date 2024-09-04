package com.springboottemplate.admin.controller;

import com.springboottemplate.domain.system.user.db.SysUserService;
import com.springboottemplate.infrastructure.cache.RedisUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "testAPI")
@RestController
public class TestController {

    @Resource
    SysUserService sysUserService;

    @GetMapping("/")
    public String getConfig() {

        return sysUserService.list().toString();
    }
    @Resource
    RedisUtil redisUtil;
    @GetMapping("/redis")
    public String redis(){
        redisUtil.setCacheObject("test","test");
        return redisUtil.getCacheObject("test").toString();
    }
}
