package com.ybjzo2o.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.customer.model.dto.BankAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankAccountMapper extends BaseMapper<BankAccount> {
}
