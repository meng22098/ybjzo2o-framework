package com.ybjzo2o.mvc.config;

import com.ybjzo2o.mvc.handler.RequestIdHandlerImpl;
import com.ybjzo2o.mvc.handler.UserInfoHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RequestIdHandlerImpl.class, UserInfoHandlerImpl.class})
public class AutoConfiguration {
}
