package com.ybjzo2o.api.customer;

import com.ybjzo2o.api.customer.dto.request.CommonUserPageQueryReqDTO;
import com.ybjzo2o.api.customer.dto.request.CommonUserUpdateReqDTO;
import com.ybjzo2o.api.customer.dto.response.CommonUserResDTO;
import com.ybjzo2o.common.model.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 内部接口 - 普通用户相关接口
 *
 * @author fjs
 */
@FeignClient(contextId = "ybjzo2o-customer", value = "ybjzo2o-customer", path = "/customer/inner/common-user")
public interface CommonUserApi {

    @GetMapping("/{id}")
    CommonUserResDTO findById(@PathVariable("id") Long id);
}
