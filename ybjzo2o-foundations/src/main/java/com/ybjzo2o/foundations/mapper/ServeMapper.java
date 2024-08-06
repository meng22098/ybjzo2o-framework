package com.ybjzo2o.foundations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import com.ybjzo2o.foundations.model.domain.Serve;
import com.ybjzo2o.foundations.model.dto.response.ServeAggregationSimpleResDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeAggregationTypeSimpleResDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeCategoryResDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;
import org.apache.ibatis.annotations.Param;

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
}
