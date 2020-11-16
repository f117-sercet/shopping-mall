package com.dsc.search.serviceImpl;

import com.dsc.common.utils.HttpUtil;
import com.dsc.mall.manager.dto.front.SearchItem;
import com.dsc.mall.manager.dto.front.SearchResult;
import com.dsc.search.search.SearchService;
import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.text.Highlighter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

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
            }else if(priceGt>=0&&priceLte>=0&&sort.equals("1")){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)	// Query
                        .setFrom(start).setSize(size).setExplain(true)	//从第几个开始，显示size个数据
                        .highlighter(highlightBuilder)		//设置高亮显示
                        .setPostFilter(QueryBuilders.rangeQuery("salePrice").gt(priceGt).lt(priceLte))	//过滤条件
                        .addSort("salePrice", SortOrder.ASC)
                        .get();
            }else if(priceGt>=0&&priceLte>=0&&sort.equals("-1")){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)	// Query
                        .setFrom(start).setSize(size).setExplain(true)	//从第几个开始，显示size个数据
                        .highlighter(highlightBuilder)		//设置高亮显示
                        .setPostFilter(QueryBuilders.rangeQuery("salePrice").gt(priceGt).lt(priceLte))	//过滤条件
                        .addSort("salePrice", SortOrder.DESC)
                        .get();
            }else if((priceGt<0||priceLte<0)&&sort.isEmpty()){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)	// Query
                        .setFrom(start).setSize(size).setExplain(true)	//从第几个开始，显示size个数据
                        .highlighter(highlightBuilder)		//设置高亮显示
                        .get();
            }else if((priceGt<0||priceLte<0)&&sort.equals("1")){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)	// Query
                        .setFrom(start).setSize(size).setExplain(true)	//从第几个开始，显示size个数据
                        .highlighter(highlightBuilder)		//设置高亮显示
                        .addSort("salePrice", SortOrder.ASC)
                        .get();
            }else if((priceGt<0||priceLte<0)&&sort.equals("-1")){
                searchResponse=client.prepareSearch(ITEM_INDEX)
                        .setTypes(ITEM_TYPE)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(qb)	// Query
                        .setFrom(start).setSize(size).setExplain(true)	//从第几个开始，显示size个数据
                        .highlighter(highlightBuilder)		//设置高亮显示
                        .addSort("salePrice", SortOrder.DESC)
                        .get();
            }


            SearchHits hits = searchResponse.getHits();
            //返回总结果数
            searchResult.setRecordCount(hits.totalHits);
            List<SearchItem> list=new ArrayList<>();
            if (hits.totalHits > 0) {
                for (SearchHit hit : hits) {
                    //总页数
                    int totalPage=(int) (hit.getScore()/size);
                    if((hit.getScore()%size)!=0){
                        totalPage++;
                    }
                    //返回结果总页数
                    searchResult.setTotalPages(totalPage);
                    //设置高亮字段
                    SearchItem searchItem=new Gson().fromJson(hit.getSourceAsString(),SearchItem.class);
                    String productName = hit.getHighlightFields().get("productName").getFragments()[0].toString();
                    searchItem.setProductName(productName);
                    //返回结果
                    list.add(searchItem);
                }
            }
            searchResult.setItemList(list);
            //因个人服务器配置过低此处取消关闭减轻搜索压力增快搜索速度
            //client.close();

            return searchResult;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String quickSearch(String key) {

        String result = null;

        try {
            result = HttpUtil.sendGet("http://"+ES_CONNECT_IP+":"+ES_NODE_CLIENT_PORT+"/item/itemList/_search?q=productName:"+key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
