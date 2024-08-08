package com.ybjzo2o.customer.service;

import com.ybjzo2o.customer.model.domain.ServeProviderInfo;
import com.ybjzo2o.customer.model.domain.ServeProviderSync;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评分同步列表 服务类
 * </p>
 *
 * @author ithyfjs
 * @since 2024-08-07
 */
public interface IServeProviderSyncService extends IService<ServeProviderSync> {

    int add(Long id, Integer serveProviderType);

    /**
     * 更新评分
     * @param id
     * @param evaluationScore
     */
    void updateScore(Long id, Double evaluationScore);



}
