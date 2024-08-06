package com.ybjzo2o.foundations.service.impl;

import com.ybjzo2o.common.constants.UserType;
import com.ybjzo2o.common.expcetions.RequestForbiddenException;
import com.ybjzo2o.common.utils.JwtTool;
import com.ybjzo2o.foundations.model.domain.Operator;
import com.ybjzo2o.foundations.model.dto.request.LoginReqDTO;
import com.ybjzo2o.foundations.service.ILoginService;
import com.ybjzo2o.foundations.service.IOperatorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fjs
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private IOperatorService operatorService;
    @Resource
    private JwtTool jwtTool;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 运营员登录
     *
     * @param loginReqDTO 运营人员登录请求模型
     * @return token
     */
    @Override
    public String login(LoginReqDTO loginReqDTO) {

        Operator operator = operatorService.findByUsername(loginReqDTO.getUsername());
        if (operator == null) {
            throw new RequestForbiddenException("账号或密码错误，请重新输入");
        }
        // 比对密码
        if (!passwordEncoder.matches(loginReqDTO.getPassword(), operator.getPassword())) {
            throw new RequestForbiddenException("账号或密码错误，请重新输入");
        }
        return jwtTool.createToken(operator.getId(), operator.getName(), operator.getAvatar(), UserType.OPERATION);
    }
}
