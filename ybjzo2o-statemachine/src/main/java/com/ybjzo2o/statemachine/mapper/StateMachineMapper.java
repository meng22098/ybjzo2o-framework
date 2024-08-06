package com.ybjzo2o.statemachine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.statemachine.model.StatePersister;
import org.apache.ibatis.annotations.Mapper;

/**
 * 状态持久化数据层
 *
 * @author hyfjs
 * @create 2023/8/5 15:36
 **/
@Mapper
public interface StateMachineMapper extends BaseMapper<StatePersister> {
}
