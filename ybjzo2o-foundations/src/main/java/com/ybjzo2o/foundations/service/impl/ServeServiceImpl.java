package com.ybjzo2o.foundations.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybjzo2o.common.expcetions.CommonException;
import com.ybjzo2o.common.expcetions.ForbiddenOperationException;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.foundations.enums.FoundationStatusEnum;
import com.ybjzo2o.foundations.mapper.RegionMapper;
import com.ybjzo2o.foundations.mapper.ServeItemMapper;
import com.ybjzo2o.foundations.mapper.ServeMapper;
import com.ybjzo2o.foundations.model.domain.Region;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.domain.ServeItem;
import com.ybjzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.ybjzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;
import com.ybjzo2o.foundations.service.IServeService;
import com.ybjzo2o.mysql.utils.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServeService {
    @Autowired
    ServeItemMapper serveItemMapper;
    @Autowired
    RegionMapper regionMapper;

    /**
     *区域服务分页查询
     * @param servePageQueryReqDTO
     * @return
     */
    @Override
    public PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO) {
        PageResult<ServeResDTO> serveResDTOPageResult = PageHelperUtils.selectPage(servePageQueryReqDTO, () ->
                baseMapper.queryServeListByRegionId(servePageQueryReqDTO.getRegionId()));
        return serveResDTOPageResult;
    }

    /**
     *区域服务批量新增
     * @param serveUpsertReqDTOList
     */
    @Transactional
    @Override
    public void batchAdd(List<ServeUpsertReqDTO> serveUpsertReqDTOList) {
        for (ServeUpsertReqDTO serveUpsertReqDTO:serveUpsertReqDTOList)
        {
            //1.校验服务项是否为启用状态，不是启用状态不能新增
           ServeItem serveItem= serveItemMapper.selectById(serveUpsertReqDTO.getServeItemId());
            //如果服务项信息不存在或未启用
            if (ObjectUtil.isNull(serveItem)||serveItem.getActiveStatus()!= FoundationStatusEnum.ENABLE.getStatus())
            {
               throw new ForbiddenOperationException("该服务未启用无法添加到区域下使用");
            }
            //2.校验是否重复新增
            Integer count = lambdaQuery()
                .eq(Serve::getRegionId, serveUpsertReqDTO.getRegionId())
                .eq(Serve::getServeItemId, serveUpsertReqDTO.getServeItemId())
                .count();
            if(count>0){
                throw new ForbiddenOperationException(serveItem.getName()+"服务已存在");
            }
            //3.新增服务
            Serve serve = BeanUtil.toBean(serveUpsertReqDTO, Serve.class);
            Region region = regionMapper.selectById(serveUpsertReqDTO.getRegionId());
            serve.setCityCode(region.getCityCode());
            baseMapper.insert(serve);
        }
    }

    /**
     * 修改价格
     * @param id
     * @param price
     * @return
     */
    @Transactional
    @Override
    public  Serve update(long id, BigDecimal price) {
        //1.更新服务价格
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getPrice, price)
                .update();
        if(!update){
            throw new CommonException("修改服务价格失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 区域服务上架
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Serve onSale(Long id) {
        Serve serve = baseMapper.selectById(id);
        if(ObjectUtil.isNull(serve)){
            throw new ForbiddenOperationException("区域服务不存在");
        }
        //上架状态
        Integer saleStatus = serve.getSaleStatus();
        //草稿或下架状态方可上架
        if (!(saleStatus==FoundationStatusEnum.INIT.getStatus()
                || saleStatus==FoundationStatusEnum.DISABLE.getStatus())) {
            throw new ForbiddenOperationException("草稿或下架状态方可上架");
        }
        //服务项id
        Long serveItemId = serve.getServeItemId();
        ServeItem serveItem = serveItemMapper.selectById(serveItemId);
        if(ObjectUtil.isNull(serveItem)){
            throw new ForbiddenOperationException("所属服务项不存在");
        }
        //服务项的启用状态
        Integer activeStatus = serveItem.getActiveStatus();
        //服务项为启用状态方可上架
        if (!(FoundationStatusEnum.ENABLE.getStatus()==activeStatus)) {
            throw new ForbiddenOperationException("服务项为启用状态方可上架");
        }
        //更新上架状态
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getSaleStatus, FoundationStatusEnum.ENABLE.getStatus())
                .update();
        if(!update){
            throw new CommonException("启动服务失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 删除区域服务
     * @param id
     * @return
     */
    @Override
    public Serve delete(Long id) {
        Serve serve = baseMapper.selectById(id);
        Integer saleStatus = serve.getSaleStatus();
        //草稿或下架状态方可删除
        if (!(saleStatus==FoundationStatusEnum.INIT.getStatus()
                || saleStatus==FoundationStatusEnum.DISABLE.getStatus())) {
            throw new ForbiddenOperationException("草稿或下架状态方可删除");
        }
        //1.删除服务
        boolean update = removeById(id);
        if(!update){
            throw new CommonException("删除服务失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 设置热门
     * @param id
     * @return
     */
    @Override
    public Serve onHot(Long id) {
        Serve serve = baseMapper.selectById(id);
        Integer saleStatus = serve.getSaleStatus();
        //草稿或下架状态不可设置热门服务
        if (saleStatus==FoundationStatusEnum.INIT.getStatus()
                || saleStatus==FoundationStatusEnum.DISABLE.getStatus()) {
            throw new ForbiddenOperationException("草稿或下架状态不可设置热门服务");
        }
        //1.设置热门服务
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getIsHot, 1)
                .update();
        if(!update){
            throw new CommonException("设置热门失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 取消热门
     * @param id
     * @return
     */
    @Override
    public Serve offHot(Long id) {
        Serve serve = baseMapper.selectById(id);
        Integer isHot = serve.getIsHot();
        if (isHot==0)
        {
            throw new ForbiddenOperationException("当前状态不是热门，只有热门状态下才可取消热门");
        }
        //1.取消热门服务
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getIsHot, 0)
                .update();
        if(!update){
            throw new CommonException("取消热门失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 区域服务下架
     * @param id
     * @return
     */
    @Override
    public Serve offSale(Long id) {
        Serve serve = baseMapper.selectById(id);
        Integer saleStatus = serve.getSaleStatus();
        //草稿或下架状态不需下架
        if (saleStatus==FoundationStatusEnum.INIT.getStatus()
                || saleStatus==FoundationStatusEnum.DISABLE.getStatus()) {
            throw new ForbiddenOperationException("草稿或下架状态不需下架");
        }
        //1.服务下架
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getSaleStatus, FoundationStatusEnum.DISABLE.getStatus())
                .update();
        if(!update){
            throw new CommonException("服务下架失败");
        }
        return baseMapper.selectById(id);
    }

    /**
     * 根据区域查询服务数量
     * @param id
     * @param status
     * @return
     */
    @Override
    public int queryServeCountByRegionIdAndSaleStatus(Long id,int status) {
        Integer count = lambdaQuery()
                .eq(Serve::getRegionId,id)
                .eq(Serve::getSaleStatus,status)
                .count();
        return count;
    }
}