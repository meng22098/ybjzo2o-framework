package com.ybjzo2o.foundations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fjs
 * @since 2024-07-03
 */
public interface ServeMapper extends BaseMapper<Serve> {
    List<ServeResDTO> queryServeListByRegionId(Long regionId);
}
