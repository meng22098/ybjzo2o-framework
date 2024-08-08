package com.ybjzo2o.foundations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.ybjzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IServeService extends IService<Serve> {
    PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO);

    void batchAdd(List<ServeUpsertReqDTO> serveUpsertReqDTOList);

    Serve update(long id, BigDecimal price);

    Serve onSale(Long id);

    Serve delete(Long id);

    Serve onHot(Long id);

    Serve offHot(Long id);

    Serve offSale(Long id);

    int queryServeCountByRegionIdAndSaleStatus(Long id, int status);

    int queryServeCountByServeItemIdAndSaleStatus(Long id, int status);
}