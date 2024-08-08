package com.ybjzo2o.customer.controller.operation;

import com.ybjzo2o.api.customer.dto.request.EvaluationSubmitReqDTO;
import com.ybjzo2o.common.model.CurrentUserInfo;
import com.ybjzo2o.customer.model.dto.request.AuditReqDTO;
import com.ybjzo2o.customer.model.dto.request.EvaluationPageByTargetReqDTO;
import com.ybjzo2o.customer.model.dto.request.LikeOrCancelReqDTO;
import com.ybjzo2o.customer.model.dto.response.EvaluationAndOrdersResDTO;
import com.ybjzo2o.customer.model.dto.response.EvaluationResDTO;
import com.ybjzo2o.customer.model.dto.response.EvaluationTokenDto;
import com.ybjzo2o.customer.service.EvaluationService;
import com.ybjzo2o.mvc.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评价相关接口
 *
 * @author ithyfjs
 * @create 2024/7/11 16:14
 **/
@RestController("operationEvaluationController")
@RequestMapping("/operation/evaluation")
@Api(tags = "运营端 - 评价相关接口")
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;


    @GetMapping("/token")
    @ApiOperation("获取评价系统token")
    public EvaluationTokenDto getToken() {
        return evaluationService.getEvaluationInfo();
    }
}
