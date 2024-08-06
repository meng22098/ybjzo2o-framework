package com.ybjzo2o.api.orders;

import com.ybjzo2o.api.orders.dto.request.OrderSeizeReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "ybjzo2o-orders-seize", value = "ybjzo2o-orders-seize", path = "/orders-seize/inner/seize")
public interface OrdersSeizeApi {

    /**
     * 机器抢单
     *
     * @param orderSeizeReqDTO
     */
    @PostMapping("")
    void machineSeize(@RequestBody OrderSeizeReqDTO orderSeizeReqDTO);

}
