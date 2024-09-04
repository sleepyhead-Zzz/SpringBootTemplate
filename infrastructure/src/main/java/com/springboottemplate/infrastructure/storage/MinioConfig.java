package com.springboottemplate.infrastructure.storage;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * description: 获取配置文件信息
 *
 * @author: weirx
 * @time: 2021/8/25 9:50
 */
@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinioConfig {

    @Resource
    private MinioPropertiesConfig minioPropertiesConfig;


    /**
     * 初始化 MinIO 客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioPropertiesConfig.getEndpoint())
            .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
            .build();
    }
}
