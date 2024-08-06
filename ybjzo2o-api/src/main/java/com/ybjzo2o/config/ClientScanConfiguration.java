package com.ybjzo2o.config;

import com.ybjzo2o.common.handler.RequestIdHandler;
import com.ybjzo2o.common.handler.UserInfoHandler;
import com.ybjzo2o.interceptor.FeignInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.ybjzo2o.api")
@Import({com.ybjzo2o.utils.MyQueryMapEncoder.class})
@ConditionalOnProperty(prefix = "feign", name = "enable", havingValue = "true")
public class ClientScanConfiguration {

    @Bean
    public FeignInterceptor feignInterceptor(UserInfoHandler userInfoHandler, RequestIdHandler requestIdHandler){
        return new FeignInterceptor(userInfoHandler, requestIdHandler);
    }


}
