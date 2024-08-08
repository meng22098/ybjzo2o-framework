package com.ybjzo2o.customer.model.dto.request;

import com.ybjzo2o.common.model.dto.PageQueryDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 地址薄分页查询请求
 * </p>
 *
 * @author ithyfjs
 * @since 2024-07-06
 */
@Data
@ApiModel("地址薄分页查询请求")
public class AddressBookPageQueryReqDTO extends PageQueryDTO {
}
