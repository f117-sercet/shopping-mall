package com.dsc.search.serviceImpl;

import com.dsc.mall.manager.dto.front.SearchResult;
import com.dsc.search.search.SearchService;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;

/**
 * Search实现类
 * @author dsc
 */
public class SearchServiceImpl implements SearchService {
    @Value("${127.0.0.1")
    private String ES_CONNEC0T_IP;

    @Value("$9200")
    private String ES_NODE_CLIENT_PORT;

    @Value("elasticsearch")
    private String ES_CLUSTER_NAME;

    @Value("item")
    private String ITEM_INDEX;

    @Value("itemList")
    private String ITEM_TYPE;

    /**
     * 使用QueryBuilder
     * 	 * termQuery("key", obj) 完全匹配
     * 	 * termsQuery("key", obj1, obj2..)   一次匹配多个值
     * 	 * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
     * 	 * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
     */
    @Override
    public SearchResult search(String keyword, int page, int size, String sort, int priceGt, int priceLte) {


        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String quickSearch(String key) {
        return null;
    }
}
