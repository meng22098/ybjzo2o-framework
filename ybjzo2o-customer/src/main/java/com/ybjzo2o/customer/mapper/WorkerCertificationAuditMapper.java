package com.ybjzo2o.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.customer.model.dto.WorkerCertificationAudit;
import com.ybjzo2o.customer.model.dto.request.WorkerCertificationAuditPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.response.WorkerCertificationAuditResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkerCertificationAuditMapper extends BaseMapper<WorkerCertificationAudit> {
    List<WorkerCertificationAuditResDTO> queryWorkerList(WorkerCertificationAuditPageQueryReqDTO workerCertificationAuditPageQueryReqDTO);
}
