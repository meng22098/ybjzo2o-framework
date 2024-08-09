package com.ybjzo2o.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybjzo2o.customer.mapper.BankAccountMapper;
import com.ybjzo2o.customer.model.dto.BankAccount;
import com.ybjzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;
import com.ybjzo2o.customer.service.IBankAccountService;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper,BankAccount> implements IBankAccountService
{
    @Override
    public void upsert(BankAccountUpsertReqDTO bankAccountUpsertReqDTO) {
        BankAccount bankAccount = BeanUtil.toBean(bankAccountUpsertReqDTO, BankAccount.class);
        super.saveOrUpdate(bankAccount);
    }
}
