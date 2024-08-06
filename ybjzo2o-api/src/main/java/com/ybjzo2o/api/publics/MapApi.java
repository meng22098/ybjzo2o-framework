package com.ybjzo2o.api.publics;

import com.ybjzo2o.api.publics.dto.response.LocationResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fjs
 */
@FeignClient(contextId = "ybjzo2o-publics", value = "ybjzo2o-publics", path = "/publics/inner/map")
public interface MapApi {

    @GetMapping("/getLocationByAddress")
    LocationResDTO getLocationByAddress(@RequestParam("address") String address);
}
