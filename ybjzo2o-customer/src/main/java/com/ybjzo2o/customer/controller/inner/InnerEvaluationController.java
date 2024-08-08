package com.ybjzo2o.customer.controller.inner;

import com.ybjzo2o.api.customer.EvaluationApi;
import com.ybjzo2o.api.customer.dto.request.EvaluationSubmitReqDTO;
import com.ybjzo2o.api.customer.dto.response.EvaluationScoreResDTO;
import com.ybjzo2o.customer.service.EvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 评价相关接口
 *
 * @author ithyfjs
 * @create 2024/7/11 16:14
 **/
@RestController("innerEvaluationController")
@RequestMapping("/inner/evaluation")
@Api(tags = "内部接口 - 评价相关接口")
public class InnerEvaluationController implements EvaluationApi {
    @Resource
    private EvaluationService evaluationService;

    @Override
    @GetMapping("/queryServeProviderScoreByOrdersId")
    @ApiOperation("根据订单id列表查询师傅评分")
    public EvaluationScoreResDTO queryServeProviderScoreByOrdersId(@RequestParam("orderIds") List<Long> orderIds) {
        Map<String, Double> scoreMap = evaluationService.queryServeProviderScoreByOrdersId(orderIds);
        return new EvaluationScoreResDTO(scoreMap);
    }

    @Override
    @PostMapping("/autoEvaluate")
    @ApiOperation("自动评价")
    public void autoEvaluate(@RequestBody EvaluationSubmitReqDTO evaluationSubmitReqDTO) {
        evaluationService.autoEvaluate(evaluationSubmitReqDTO);
    }
}
