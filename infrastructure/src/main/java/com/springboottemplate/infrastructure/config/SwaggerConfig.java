package com.springboottemplate.infrastructure.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.HashMap;
import java.util.Map;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String Token = "Authorization";

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }

            if (openApi.getPaths() != null) {
                openApi.addExtension("x-test123", "333");
                openApi.getPaths().addExtension("x-abb", RandomUtil.randomInt(1, 100));
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .info(new Info()
                .title("SpringBoot模板项目")
                .version("1.0")
                .description("SpringBoot模板项目"))

            .components(new Components().addSecuritySchemes(
                    Token,
                    new SecurityScheme()
                        .name(Token)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .description(
                            "Token")
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                )
            );
    }

}
