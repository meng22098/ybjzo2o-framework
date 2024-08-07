package com.ybjzo2o.foundations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;

public interface IServeService extends IService<Serve> {
    PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO);
}