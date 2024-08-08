package com.ybjzo2o.customer.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class EsTest {

//    @Resource
//    private ElasticSearchTemplate elasticSearchTemplate;
//
//    @Resource
//    private IServeProviderSyncService serveProviderSyncService;
//
//    public EsTest() {
//    }
//
//    @Test
//    public void testBatchInsert() {
//
//        List<ServeProviderSync> data = serveProviderSyncService.list();
//        List<ServeProviderInfo> serveProviderInfos = BeanUtils.copyToList(data, ServeProviderInfo.class, (sync, info) -> {
//            info.setLocation(new Location(sync.getLon(), sync.getLat()));
//        });
//
//
//        Boolean batchInsertResult = elasticSearchTemplate.opsForDoc().batchInsert("serve_provider_info_1", serveProviderInfos);
//        log.info("batchInsertResult:{}", batchInsertResult);
//    }
}
