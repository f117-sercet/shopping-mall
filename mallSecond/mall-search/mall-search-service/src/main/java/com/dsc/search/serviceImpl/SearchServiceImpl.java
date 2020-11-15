package com.dsc.search.serviceImpl;

import com.dsc.mall.manager.dto.front.SearchResult;
import com.dsc.search.search.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.text.Highlighter;
import java.net.InetAddress;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Search实现类
 * @author dsc
 */
public class SearchServiceImpl implements SearchService {
    @Value("${127.0.0.1")
    private String ES_CONNECT_IP;

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
            Settings settings = Settings.builder()
                    .put("cluster.name", ES_CLUSTER_NAME).build();
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(ES_CONNECT_IP), 9300));

            SearchResult searchResult=new SearchResult();

            //设置查询条件
            //单字段搜索
            QueryBuilder qb = matchQuery("productName",keyword);

            //设置分页
            if (page <=0 ){
                page =1;
            }
            int start=(page - 1) * size;
            //设置高亮显示
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.preTags("<a style=\"color: #e4393c\">");
            highlightBuilder.postTags("</a>");
            highlightBuilder.field("product");

            //执行搜索
            SearchResponse searchResponse = null;

            if(priceGt>=0&&priceLte>=0&&sort.isEmpty()) {
                searchResponse = client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.QUERY_THEN_FETCH)
                        /**
                         * Query
                         */
                        .setQuery(qb)
                        /**
                         * 从第几个开始，显示size个数据
                         */
                        .setFrom(start).setSize(size).setExplain(true)
                        /**
                         * 设置高亮显示
                         */
                        .highlighter(highlightBuilder)
                        .setPostFilter(QueryBuilders.rangeQuery("saleProduct"))
                        .get();
            }else if (priceGt>=0&&priceLte>=0&&sort.equals("1")){

                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        /**
                         * / Query
                         */
                        .setQuery(qb)
                        /**
                         * //从第几个开始，显示size个数据
                         */
                        .setFrom(start).setSize(size).setExplain(true)
                        /**
                         * //设置高亮显示
                         */
                        .highlighter(highlightBuilder)
                        /**
                         * //过滤条件
                         */
                        .setPostFilter(QueryBuilders.rangeQuery("salePrice").gt(priceGt).lt(priceLte))
                        .addSort("salePrice", SortOrder.ASC)
                        .get();
            }else if (priceGt>0&&priceLte>=0&&sort.equals("-1")){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)
                        .setFrom(start).setSize(size).setExplain(true)
                        .highlighter(highlightBuilder)
                        /**
                         * 过滤条件
                         */
                        .setPostFilter(QueryBuilders.rangeQuery("salePrice").gt(priceGt).lt(priceLte))
                        .addSort("salePrice", SortOrder.DESC)
                        .get();
            }

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
