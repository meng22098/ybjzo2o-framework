package com.ybjzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.customer.model.dto.AgencyCertificationAudit;
import com.ybjzo2o.customer.model.dto.request.AgencyCertificationAuditAddReqDTO;
import com.ybjzo2o.customer.model.dto.request.AgencyCertificationAuditPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.request.CertificationAuditReqDTO;
import com.ybjzo2o.customer.model.dto.response.AgencyCertificationAuditResDTO;
import com.ybjzo2o.customer.model.dto.response.RejectReasonResDTO;

public interface IAgencyCertificationAuditService extends IService<AgencyCertificationAudit> {
    void applyCertification(AgencyCertificationAuditAddReqDTO agencyCertificationAuditAddReqDTO);

    RejectReasonResDTO queryCurrentUserLastRejectReason();

    PageResult<AgencyCertificationAuditResDTO> pageQuery(AgencyCertificationAuditPageQueryReqDTO agencyCertificationAuditPageQueryReqDTO);

    void auditCertification(Long id, CertificationAuditReqDTO certificationAuditReqDTO);
}
