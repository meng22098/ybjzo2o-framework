package com.ybjzo2o.api.foundations;

import com.ybjzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "ybjzo2o-foundations", value = "ybjzo2o-foundations", path = "/foundations/inner/serve")
public interface ServeApi {

    @GetMapping("/{id}")
    ServeAggregationResDTO findById(@PathVariable("id") Long id);

}
