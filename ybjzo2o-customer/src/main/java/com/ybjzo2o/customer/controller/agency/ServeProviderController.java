package com.ybjzo2o.customer.controller.agency;


import com.ybjzo2o.api.publics.SmsCodeApi;
import com.ybjzo2o.customer.model.dto.request.InstitutionRegisterReqDTO;
import com.ybjzo2o.customer.model.dto.response.ServeProviderInfoResDTO;
import com.ybjzo2o.customer.service.IServeProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 服务人员/机构表 前端控制器
 * </p>
 *
 * @author ithyfjs
 * @since 2024-07-17
 */
@RestController("agencyServeProviderController")
@RequestMapping("/agency/serve-provider")
@Api(tags = "机构端 - 服务人员或机构相关接口")
public class ServeProviderController {
    @Resource
    private IServeProviderService serveProviderService;
    @Resource
    private SmsCodeApi smsCodeApi;
    @GetMapping("/currentUserInfo")
    @ApiOperation("获取当前用户信息")
    public ServeProviderInfoResDTO currentUserInfo() {
        return serveProviderService.currentUserInfo();
    }
    @PostMapping("/institution/resetPassword")
    @ApiOperation("忘记密码")
    public void resetPassword(@RequestBody InstitutionRegisterReqDTO institutionRegisterReqDTO) {
        serveProviderService.resetPassword(institutionRegisterReqDTO.getPhone(),3,institutionRegisterReqDTO.getVerifyCode());
    }
}
