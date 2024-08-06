package com.ybjzo2o.es.core;

import com.ybjzo2o.es.core.operations.DocumentOperations;
import com.ybjzo2o.es.core.operations.IndexOperations;

public interface ElasticSearchTemplate {
    DocumentOperations opsForDoc();
    IndexOperations opsForIndex();
}
