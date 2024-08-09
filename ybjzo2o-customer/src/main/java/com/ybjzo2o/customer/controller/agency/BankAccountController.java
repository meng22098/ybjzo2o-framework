package com.ybjzo2o.customer.controller.agency;

import cn.hutool.core.bean.BeanUtil;
import com.ybjzo2o.common.model.CurrentUserInfo;
import com.ybjzo2o.customer.model.dto.BankAccount;
import com.ybjzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;
import com.ybjzo2o.customer.model.dto.response.BankAccountResDTO;
import com.ybjzo2o.customer.service.IBankAccountService;
import com.ybjzo2o.mvc.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("agencyBankAccountController")
@RequestMapping("/agency/bank-account")
@Api(tags = "机构端 - 银行账户信息相关接口")
public class BankAccountController {
    @Resource
    private IBankAccountService bankAccountService;
    @PostMapping
    @ApiOperation("新增或更新银行账号信息")
    public void queryByServeProviderId(@RequestBody BankAccountUpsertReqDTO bankAccountUpsertReqDTO) {
        CurrentUserInfo currentUserInfo = UserContext.currentUser();
        bankAccountUpsertReqDTO.setId(currentUserInfo.getId());
        bankAccountUpsertReqDTO.setType(currentUserInfo.getUserType());
        bankAccountService.upsert(bankAccountUpsertReqDTO);
    }
    @GetMapping("/currentUserBankAccount")
    @ApiOperation("获取当前用户银行账号")
    public BankAccountResDTO queryCurrentUserBankAccount() {
        BankAccount bankAccount =
                bankAccountService.getById(UserContext.currentUserId());
        return BeanUtil.toBean(bankAccount, BankAccountResDTO.class);
    }
}