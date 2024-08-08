package com.ybjzo2o.publics.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import java.util.Map;
/**
 * 短信发送工具类
 */
public class SMSUtils {
    // 发送短信验证码，是你自己申请的模板编号，在阿里云可以看到（要替换）
    public static final String VALIDATE_CODE = "SMS_302805999";
    // 理解为阿里云的账号密码
// 您的AccessKey ID（要替换）
    private static String accessKeyId = "";
    // 您的AccessKey Secret（要替换）
    private static String accessKeySecret = "";
    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private static Client createClient(String accessKeyId, String accessKeySecret)
            throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
    /**
     * 发送短信
     *
     * @param telephone
     * @param signName
    电话号码
    签名
     * @param templateCode 模板编号
     * @param code
    验证码
     * @return 返回的状态码为OK，则发送成功
     */
    public static String send(String telephone, String signName, String templateCode, String code) {
        try {
            Client client = SMSUtils.createClient(accessKeyId, accessKeySecret);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(telephone)
                    .setSignName(signName)  // 短信签名
                    .setTemplateCode(templateCode)  // 模板编号
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            // 发送短信，获取发送结果
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            // 将结果转成Map对象
            Map<String, Object> map = sendSmsResponse.toMap();
            // 获取主体部分
            Map<String, String> body = (Map<String, String>) map.get("body");
            System.out.println(body);
            return body.get("Code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 失败返回空
        return null;
    }
    /**
     * 调用方式
     *
     * @param args
     */
    public static void main(String[] args) {
        String code = SMSUtils.send("17600477102", "黑马程序员广州校区", "ORDER_NOTICE",
                "520520");
        System.out.println(code);
    }
}
