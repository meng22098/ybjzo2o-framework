package com.ybjzo2o.foundations.controller.operation;

import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.ybjzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.ybjzo2o.foundations.model.dto.response.ServeResDTO;
import com.ybjzo2o.foundations.service.IServeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController("operationServeController")
@RequestMapping("/operation/serve")
@Slf4j
@Api(tags = "运营端 - 区域服务相关接口")
public class ServeController {
    @Autowired
    IServeService serveService;
    @PostMapping("/batch")
    @ApiOperation("区域服务批量新增")
    public void add(@RequestBody List<ServeUpsertReqDTO> serveUpsertReqDTOList)
    {
        serveService.batchAdd(serveUpsertReqDTOList);
    }
    @GetMapping("/page")
    @ApiOperation("区域服务分页查询")
    public PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO) {
        PageResult<ServeResDTO> page = serveService.page(servePageQueryReqDTO);
        return page;
    }
    @PutMapping("/{id}")
    @ApiOperation("修改价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "price", value = "价格", required = true, dataTypeClass = BigDecimal.class)
    })
    public void updatePrice(@PathVariable("id") long id,@RequestParam("price") BigDecimal price)
    {
        serveService.update(id,price);
    }
    @PutMapping("/onSale/{id}")
    @ApiOperation("区域服务上架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
    })
    public void onSale(@PathVariable("id") Long id) {
        serveService.onSale(id);
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除区域服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
    })
    public void delete(@PathVariable("id") Long id)
    {
        serveService.delete(id);
    }
    @PutMapping("/offSale/{id}")
    @ApiOperation("区域服务下架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
    })
    public void offSale(@PathVariable("id") Long id)
    {
        serveService.offSale(id);
    }
    @PutMapping("/onHot/{id}")
    @ApiOperation("设置热门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
    })
    public void onHot(@PathVariable("id") Long id)
    {
        serveService.onHot(id);
    }
    @PutMapping("/offHot/{id}")
    @ApiOperation("取消热门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataTypeClass = Long.class),
    })
    public void offHot(@PathVariable("id") Long id)
    {
        serveService.offHot(id);
    }
}
