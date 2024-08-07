package com.ybjzo2o.foundations.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.foundations.mapper.ServeMapper;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;
import com.ybjzo2o.foundations.service.IServeService;
import com.ybjzo2o.mysql.utils.PageHelperUtils;
import org.springframework.stereotype.Service;

@Service
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServeService {

    @Override
    public PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO) {
        PageResult<ServeResDTO> serveResDTOPageResult = PageHelperUtils.selectPage(servePageQueryReqDTO, () ->
                baseMapper.queryServeListByRegionId(servePageQueryReqDTO.getRegionId()));
        return serveResDTOPageResult;
    }
}