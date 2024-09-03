package com.springboottemplate.admin.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@MapperScan(value = "com.springboottemplate.**.db", markerInterface = com.baomidou.mybatisplus.core.mapper.BaseMapper.class)
public class ApplicationConfig {

}

