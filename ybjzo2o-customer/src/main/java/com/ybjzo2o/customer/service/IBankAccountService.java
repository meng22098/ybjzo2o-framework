package com.ybjzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.customer.model.dto.BankAccount;
import com.ybjzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;

public interface IBankAccountService extends IService<BankAccount> {
    void upsert(BankAccountUpsertReqDTO bankAccountUpsertReqDTO);
}
