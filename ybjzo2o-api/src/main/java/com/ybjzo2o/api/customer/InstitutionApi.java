package com.ybjzo2o.api.customer;

import com.ybjzo2o.api.customer.dto.InstitutionUserPageQueryDTO;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.api.customer.dto.InstitutionUserPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 机构相关列表
 */
@FeignClient(contextId = "ybjzo2o-customer", value = "ybjzo2o-customer", path = "/customer/inner/institutions/user")
public interface InstitutionApi {

    /**
     * 分页查询机构用户信息
     *
     * @param institutionUserPageQueryDTO 查询条件
     * @return
     */
    @GetMapping("")
    PageResult<InstitutionUserPageDTO> queryForAdmin(@SpringQueryMap InstitutionUserPageQueryDTO institutionUserPageQueryDTO);
}
