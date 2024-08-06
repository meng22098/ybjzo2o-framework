package com.ybjzo2o.mvc.config;

import com.ybjzo2o.mvc.filter.PackResultFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author hyfjs
 */
@Configuration
@Import(PackResultFilter.class)
public class FilterConfiguration {
}
