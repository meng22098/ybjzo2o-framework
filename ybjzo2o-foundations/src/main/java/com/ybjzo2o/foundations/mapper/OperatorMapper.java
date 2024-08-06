package com.ybjzo2o.foundations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.foundations.model.domain.Operator;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 运营人员 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-03
 */
public interface OperatorMapper extends BaseMapper<Operator> {

    @Select("select * from operator")
    List<Operator> queryAll();
}
