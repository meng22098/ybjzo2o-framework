package com.ybjzo2o.customer.controller.open;

import com.ybjzo2o.api.publics.SmsCodeApi;
import com.ybjzo2o.customer.model.dto.request.InstitutionRegisterReqDTO;
import com.ybjzo2o.customer.service.IServeProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("openRegisterController")
@RequestMapping("/open/serve-provider")
@Api(tags = "白名单接口 - 服务人员注册相关接口")
public class RegisterController {
    @Autowired
    IServeProviderService serveProviderService;
    @Resource
    private SmsCodeApi smsCodeApi;
    @PostMapping("/institution/register")
    @ApiOperation("服务人员/机构人员注册接口")
    public void register(@RequestBody InstitutionRegisterReqDTO institutionRegisterReqDTO) {
        serveProviderService.register(institutionRegisterReqDTO);
    }
}
