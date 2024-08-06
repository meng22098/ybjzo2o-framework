package com.ybjzo2o.publics.service;

import com.ybjzo2o.common.enums.SmsBussinessTypeEnum;
import com.ybjzo2o.publics.model.dto.request.SmsCodeSendReqDTO;

public interface ISmsCodeService {

    /**
     * 发送短信验证码
     * @param smsCodeSendReqDTO
     */
    void smsCodeSend(SmsCodeSendReqDTO smsCodeSendReqDTO);

    /**
     * 校验短信验证码
     * @param phone 验证手机号
     * @param bussinessType 业务类型
     * @return 验证结果
     */
    boolean verify(String phone, SmsBussinessTypeEnum bussinessType, String verifyCode);
}
