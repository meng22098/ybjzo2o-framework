package com.ybjzo2o.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybjzo2o.common.model.CurrentUserInfo;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.customer.enums.CertificationStatusEnum;
import com.ybjzo2o.customer.mapper.WorkerCertificationAuditMapper;
import com.ybjzo2o.customer.model.domain.WorkerCertification;
import com.ybjzo2o.customer.model.dto.WorkerCertificationAudit;
import com.ybjzo2o.customer.model.dto.WorkerCertificationUpdateDTO;
import com.ybjzo2o.customer.model.dto.request.CertificationAuditReqDTO;
import com.ybjzo2o.customer.model.dto.request.WorkerCertificationAuditAddReqDTO;
import com.ybjzo2o.customer.model.dto.request.WorkerCertificationAuditPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.response.RejectReasonResDTO;
import com.ybjzo2o.customer.model.dto.response.WorkerCertificationAuditResDTO;
import com.ybjzo2o.customer.service.IServeProviderService;
import com.ybjzo2o.customer.service.IWorkerCertificationAuditService;
import com.ybjzo2o.customer.service.IWorkerCertificationService;
import com.ybjzo2o.mvc.utils.UserContext;
import com.ybjzo2o.mysql.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class WorkerCertificationAuditServiceImpl extends ServiceImpl<WorkerCertificationAuditMapper,WorkerCertificationAudit> implements IWorkerCertificationAuditService {
    @Resource
    private IWorkerCertificationService workerCertificationService;
    @Resource
    private IServeProviderService serveProviderService;

    /**
     * 新增申请资质认证记录
     * @param workerCertificationAuditAddReqDTO
     */
    @Override
    @Transactional
    public void applyCertification(WorkerCertificationAuditAddReqDTO workerCertificationAuditAddReqDTO) {
        //1.新增申请资质认证记录
        WorkerCertificationAudit workerCertificationAudit = BeanUtil.toBean(workerCertificationAuditAddReqDTO,WorkerCertificationAudit.class);
        //默认未审核
        workerCertificationAudit.setAuditStatus(0);
        workerCertificationAudit.setCertificationStatus(1);
        baseMapper.insert(workerCertificationAudit);
        //查询认证记录
        Long serveProviderId = workerCertificationAuditAddReqDTO.getServeProviderId();
        WorkerCertification workerCertification = workerCertificationService.getById(serveProviderId);
        if(ObjectUtil.isNotNull(workerCertification)){
            //2.将认证信息状态更新为认证中
            workerCertification.setCertificationStatus(CertificationStatusEnum.PROGRESSING.getStatus());//认证中
            workerCertificationService.updateById(workerCertification);
        }else{
            workerCertification = new WorkerCertification();
            workerCertification.setId(serveProviderId);
            workerCertification.setCertificationStatus(CertificationStatusEnum.PROGRESSING.getStatus());//认证中
            workerCertificationService.save(workerCertification);
        }
    }
    /**
     * 查询当前用户最近驳回原因
     *
     * @return 驳回原因
     */
    @Override
    public RejectReasonResDTO queryCurrentUserLastRejectReason() {
        LambdaQueryWrapper<WorkerCertificationAudit> queryWrapper = Wrappers.<WorkerCertificationAudit>lambdaQuery().
                eq(WorkerCertificationAudit::getServeProviderId, UserContext.currentUserId())
                .orderByDesc(WorkerCertificationAudit::getCreateTime)
                .last("limit 1");
        WorkerCertificationAudit workerCertificationAudit = baseMapper.selectOne(queryWrapper);
        return new RejectReasonResDTO(workerCertificationAudit.getRejectReason());
    }
    /**
     * 分页查询
     *
     * @param workerCertificationAuditPageQueryReqDTO 分页查询条件
     * @return 分页结果
     */
    @Override
    public PageResult<WorkerCertificationAuditResDTO> pageQuery(WorkerCertificationAuditPageQueryReqDTO workerCertificationAuditPageQueryReqDTO) {
        Page<WorkerCertificationAudit> page = PageUtils.parsePageQuery(workerCertificationAuditPageQueryReqDTO, WorkerCertificationAudit.class);
        LambdaQueryWrapper<WorkerCertificationAudit> queryWrapper = Wrappers.<WorkerCertificationAudit>lambdaQuery()
                .eq(ObjectUtil.isNotEmpty(workerCertificationAuditPageQueryReqDTO.getName()), WorkerCertificationAudit::getName, workerCertificationAuditPageQueryReqDTO.getName())
                .eq(ObjectUtil.isNotEmpty(workerCertificationAuditPageQueryReqDTO.getIdCardNo()), WorkerCertificationAudit::getIdCardNo, workerCertificationAuditPageQueryReqDTO.getIdCardNo())
                .eq(ObjectUtil.isNotEmpty(workerCertificationAuditPageQueryReqDTO.getAuditStatus()), WorkerCertificationAudit::getAuditStatus, workerCertificationAuditPageQueryReqDTO.getAuditStatus())
                .eq(ObjectUtil.isNotEmpty(workerCertificationAuditPageQueryReqDTO.getCertificationStatus()), WorkerCertificationAudit::getCertificationStatus, workerCertificationAuditPageQueryReqDTO.getCertificationStatus());
        Page<WorkerCertificationAudit> serveTypePage = baseMapper.selectPage(page, queryWrapper);
        return PageUtils.toPage(serveTypePage, WorkerCertificationAuditResDTO.class);
    }
    /**
     * 审核认证信息
     *
     * @param id                       申请记录id
     * @param certificationAuditReqDTO 审核请求
     */
    @Override
    public void auditCertification(Long id, CertificationAuditReqDTO certificationAuditReqDTO) {
        //1.更新申请记录
        CurrentUserInfo currentUserInfo = UserContext.currentUser();
        LambdaUpdateWrapper<WorkerCertificationAudit> updateWrapper = Wrappers.<WorkerCertificationAudit>lambdaUpdate()
                .eq(WorkerCertificationAudit::getId, id)
                .set(WorkerCertificationAudit::getAuditStatus, 1)//已审核
                .set(WorkerCertificationAudit::getAuditorId, currentUserInfo.getId())//审核人id
                .set(WorkerCertificationAudit::getAuditorName, currentUserInfo.getName())//审核人名称
                .set(WorkerCertificationAudit::getAuditTime, LocalDateTime.now())//审核时间
                .set(WorkerCertificationAudit::getCertificationStatus, certificationAuditReqDTO.getCertificationStatus())//认证状态
                .set(ObjectUtil.isNotEmpty(certificationAuditReqDTO.getRejectReason()), WorkerCertificationAudit::getRejectReason, certificationAuditReqDTO.getRejectReason());//驳回原因
        super.update(updateWrapper);

        //2.更新认证信息，如果认证成功，需要将各认证属性也更新
        WorkerCertificationAudit workerCertificationAudit = baseMapper.selectById(id);
        WorkerCertificationUpdateDTO workerCertificationUpdateDTO = new WorkerCertificationUpdateDTO();
        workerCertificationUpdateDTO.setId(workerCertificationAudit.getServeProviderId());
        workerCertificationUpdateDTO.setCertificationStatus(certificationAuditReqDTO.getCertificationStatus());
        if (ObjectUtil.equal(CertificationStatusEnum.SUCCESS.getStatus(), certificationAuditReqDTO.getCertificationStatus())) {
            //如果认证成功，需要更新服务人员/机构名称
            serveProviderService.updateNameById(workerCertificationAudit.getServeProviderId(), workerCertificationAudit.getName());
            workerCertificationUpdateDTO.setName(workerCertificationAudit.getName());
            workerCertificationUpdateDTO.setIdCardNo(workerCertificationAudit.getIdCardNo());
            workerCertificationUpdateDTO.setFrontImg(workerCertificationAudit.getFrontImg());
            workerCertificationUpdateDTO.setBackImg(workerCertificationAudit.getBackImg());
            workerCertificationUpdateDTO.setCertificationMaterial(workerCertificationAudit.getCertificationMaterial());
            workerCertificationUpdateDTO.setCertificationTime(workerCertificationAudit.getAuditTime().toLocalDateTime());
        }
        workerCertificationService.updateById(workerCertificationUpdateDTO);
    }
}