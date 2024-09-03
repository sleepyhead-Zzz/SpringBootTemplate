package com.springboottemplate.admin.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.springboottemplate.**.db", markerInterface = com.baomidou.mybatisplus.core.mapper.BaseMapper.class)
public class ApplicationConfig {

}

