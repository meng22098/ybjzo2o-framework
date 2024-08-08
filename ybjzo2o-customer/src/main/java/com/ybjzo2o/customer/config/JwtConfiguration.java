package com.ybjzo2o.customer.config;

import com.ybjzo2o.common.utils.JwtTool;
import com.ybjzo2o.customer.properties.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class JwtConfiguration {

    @Resource
    private ApplicationProperties applicationProperties;

    @Bean
    public JwtTool jwtTool() {
        return new JwtTool(applicationProperties.getJwtKey());
    }
}
