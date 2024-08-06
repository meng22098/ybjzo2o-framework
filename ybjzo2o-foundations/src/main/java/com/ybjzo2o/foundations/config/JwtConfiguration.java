package com.ybjzo2o.foundations.config;

import com.ybjzo2o.common.utils.JwtTool;
import com.ybjzo2o.foundations.properties.ApplicaitonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author fjs
 */
@Configuration
public class JwtConfiguration {

    @Resource
    private ApplicaitonProperties applicaitonProperties;

    @Bean
    public JwtTool jwtTool() {
        return new JwtTool(applicaitonProperties.getJwtKey());
    }
}
