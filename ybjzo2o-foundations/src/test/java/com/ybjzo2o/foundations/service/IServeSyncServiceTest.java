//package com.ybjzo2o.foundations.service;
//
//import com.ybjzo2o.es.core.ElasticSearchTemplate;
//import com.ybjzo2o.foundations.constants.IndexConstants;
//import com.ybjzo2o.foundations.model.domain.ServeSync;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//class IServeSyncServiceTest {
//    @Resource
//    private ElasticSearchTemplate elasticSearchTemplate;
//    @Resource
//    private IServeSyncService serveSyncService;
//
//    @Test
//    void syncEs() {
//        List<ServeSync> list = serveSyncService.list();
//        elasticSearchTemplate.opsForDoc().batchInsert(IndexConstants.SERVE, list);
//    }
//}