package com.ybjzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.customer.model.domain.AgencyCertification;
import com.ybjzo2o.customer.model.dto.AgencyCertificationUpdateDTO;
import com.ybjzo2o.customer.model.dto.response.AgencyCertificationResDTO;

/**
 * <p>
 * 机构认证信息表 服务类
 * </p>
 *
 * @author ithyfjs
 * @since 2024-09-06
 */
public interface IAgencyCertificationService extends IService<AgencyCertification> {


    /**
     * 根据机构id更新
     *
     * @param agencyCertificationUpdateDTO 机构认证更新模型
     */
    void updateByServeProviderId(AgencyCertificationUpdateDTO agencyCertificationUpdateDTO);


}
