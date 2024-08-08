package com.ybjzo2o.customer.service.impl;

import com.ybjzo2o.customer.mapper.ServeProviderSyncMapper;
import com.ybjzo2o.customer.model.domain.ServeProviderSync;
import com.ybjzo2o.customer.service.IServeProviderSyncService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 评分同步列表 服务实现类
 * </p>
 *
 * @author ithyfjs
 * @since 2024-08-07
 */
@Service
public class ServeProviderSyncServiceImpl extends ServiceImpl<ServeProviderSyncMapper, ServeProviderSync> implements IServeProviderSyncService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Long id, Integer serveProviderType) {
        ServeProviderSync serveProviderSync = new ServeProviderSync();
        serveProviderSync.setId(id);
        serveProviderSync.setServeProviderType(serveProviderType);
        serveProviderSync.setStatus(0);//默认0
        return baseMapper.insert(serveProviderSync);
    }

    @Override
    public void updateScore(Long id, Double evaluationScore) {
        lambdaUpdate()
                .set(ServeProviderSync::getEvaluationScore, evaluationScore)
                .eq(ServeProviderSync::getId, id)
                .update();
    }


}
