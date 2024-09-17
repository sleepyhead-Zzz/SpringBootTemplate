package com.springboottemplate.infrastructure.config;


import static com.springboottemplate.infrastructure.config.SwaggerConfig.Token;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

/**
 * @author: yhx
 * @description 为每个接口添加鉴权
 * @date: 2024-06-24 13:51
 */
@Slf4j
@Component
public class Knife4jOperationCustomizer implements GlobalOperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        List<SecurityRequirement> security = operation.getSecurity();
        if (security == null) {
            security = List.of(new SecurityRequirement().addList(Token));
            operation.setSecurity(security);
        }
        return operation;
    }
}