package com.ybjzo2o.customer.model.dto;

import lombok.Data;

/**
 * 关联id对应评分数据
 *
 * @author ithyfjs
 * @create 2024/7/16 10:28
 **/
@Data
public class RelationScoreDTO {
    /**
     * 关联id
     */
    private String relationId;
    
    /**
     * 评分
     */
    private Double score;
}