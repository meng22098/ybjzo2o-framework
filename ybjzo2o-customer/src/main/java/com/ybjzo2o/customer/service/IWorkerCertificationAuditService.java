package com.ybjzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.customer.model.dto.WorkerCertificationAudit;
import com.ybjzo2o.customer.model.dto.request.CertificationAuditReqDTO;
import com.ybjzo2o.customer.model.dto.request.WorkerCertificationAuditAddReqDTO;
import com.ybjzo2o.customer.model.dto.request.WorkerCertificationAuditPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.response.RejectReasonResDTO;
import com.ybjzo2o.customer.model.dto.response.WorkerCertificationAuditResDTO;

public interface IWorkerCertificationAuditService extends IService<WorkerCertificationAudit> {
    void applyCertification(WorkerCertificationAuditAddReqDTO workerCertificationAuditAddReqDTO);

    RejectReasonResDTO queryCurrentUserLastRejectReason();

    PageResult<WorkerCertificationAuditResDTO> pageQuery(WorkerCertificationAuditPageQueryReqDTO workerCertificationAuditPageQueryReqDTO);

    void auditCertification(Long id, CertificationAuditReqDTO certificationAuditReqDTO);
}