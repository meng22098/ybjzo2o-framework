package com.ybjzo2o.customer.model.dto;

import lombok.Data;

/**
 * 服务技能简略信息
 *
 * @author ithyfjs
 * @create 2024/7/6 17:12
 **/
@Data
public class ServeSkillSimpleDTO {
    /**
     * 服务类型名称
     */
    private String serveTypeName;

    /**
     * 服务项名称
     */
    private String serveItemName;
}
