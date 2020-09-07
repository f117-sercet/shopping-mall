package com.dsc.supergo.search.service;

import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.search.eneity.GoodsEntity;
import com.dsc.supergo.search.eneity.SearchResult;
import com.dsc.supergo.search.mapper.goodsMappers;
import com.dsc.supergo.search.repository.GoodsRepostory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 查询Service类
 * @author dsc
 */
@Service
public class SearchService {

    @Autowired
    private goodsMappers goodsMapper;

    @Autowired
    private GoodsRepostory goodsRepostory;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导入索引库
     * @return
     */

    public HttpResult importGoods() {
        List<GoodsEntity> list = goodsMapper.getGoodsList();

        System.out.println("导入数据开始。。。。");
        list.forEach(g -> {
            goodsRepostory.save(g);
        });
        System.out.println("导入数据完成。。。。");
        return HttpResult.ok();
    }

    public SearchResult search(String keyword, Map<String, String> filters, int page, int size) {
        System.out.println(filters);
        //创建聚合查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                //.withQuery(QueryBuilders.multiMatchQuery(keyword, "goods_name"))
                //设置分页信息
                .withPageable(PageRequest.of(page, size))
                //添加聚合条件
                .addAggregation(AggregationBuilders.terms("category_aggs").field("cname3"))
                .addAggregation(AggregationBuilders.terms("brand_aggs").field("brand_name"))
                //设置高亮显示
                .withHighlightFields(new HighlightBuilder.Field("name").preTags("<em>").postTags("</em>"));
        //设置查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.multiMatchQuery(keyword, "goods_name"));
        //判断是否有过滤条件
    /*if (filters != null && !filters.isEmpty()) {
        filters.keySet().forEach(key->{
            queryBuilder.withFilter(QueryBuilders.termQuery(key, filters.get(key)));
        });
    }*/
        if (filters != null && !filters.isEmpty()) {
            filters.keySet().forEach(key->{
                boolQueryBuilder.filter(QueryBuilders.termQuery(key, filters.get(key)));
            });
        }
        //设置查询条件
        queryBuilder.withQuery(boolQueryBuilder);
        //创建查询对象
        NativeSearchQuery searchQuery = queryBuilder.build();
        //执行查询
        AggregatedPage<GoodsEntity> sResult = elasticsearchTemplate.queryForPage(searchQuery, GoodsEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<GoodsEntity> goodsList = new ArrayList<>();
                SearchHits searchHits = searchResponse.getHits();
                searchHits.forEach(hits->{
                    GoodsEntity goodsEntity = new GoodsEntity();
                    goodsEntity.setId((Long)hits.getSourceAsMap().get("id"));
                    goodsEntity.setGoods_name((String)hits.getSourceAsMap().get("goods_name"));
                    goodsEntity.setSeller_id((String)hits.getSourceAsMap().get("seller_id"));
                    goodsEntity.setNick_name((String)hits.getSourceAsMap().get("nick_name"));
                    goodsEntity.setBrand_id((Integer)hits.getSourceAsMap().get("brand_id"));
                    goodsEntity.setBrand_name((String)hits.getSourceAsMap().get("brand_name"));
                    goodsEntity.setCategory1_id((Integer)hits.getSourceAsMap().get("category1_id"));
                    goodsEntity.setCname1((String)hits.getSourceAsMap().get("cname1"));
                    goodsEntity.setCategory2_id((Integer)hits.getSourceAsMap().get("category2_id"));
                    goodsEntity.setCname2((String)hits.getSourceAsMap().get("cname2"));
                    goodsEntity.setCategory3_id((Integer)hits.getSourceAsMap().get("category3_id"));
                    goodsEntity.setCname3((String)hits.getSourceAsMap().get("cname3"));
                    goodsEntity.setSmall_pic((String)hits.getSourceAsMap().get("small_pic"));
                    goodsEntity.setPrice((Double) hits.getSourceAsMap().get("price"));
                    //取高亮结果
                    HighlightField highlightField = hits.getHighlightFields().get("goods_name");
                    if (highlightField != null) {
                        String hl = highlightField.getFragments()[0].string();
                        goodsEntity.setGoods_name(hl);
                    }
                    goodsList.add(goodsEntity);
                });
                AggregatedPage<T> aggregatedPage = new AggregatedPageImpl<T>((List<T>) goodsList, pageable, searchHits.totalHits, searchResponse.getAggregations());
                return aggregatedPage;
            }
        });
        //取查询结果
        List<GoodsEntity> content = sResult.getContent();
        SearchResult searchResult = new SearchResult();
        searchResult.setGoodsList(content);
        //取分类聚合结果
        Terms termsCat = (Terms) sResult.getAggregation("category_aggs");
        List<String> catAggsList = termsCat.getBuckets().stream().map(e -> e.getKeyAsString()).collect(Collectors.toList());
        Map catAggsMap = new HashMap();
        catAggsMap.put("name","分类");
        catAggsMap.put("field", "cname3");
        catAggsMap.put("content", catAggsList);
        //取品牌聚合结果
        Terms termsBrand = (Terms) sResult.getAggregation("brand_aggs");
        List<String> brandAggsList = termsBrand.getBuckets().stream().map(e -> e.getKeyAsString()).collect(Collectors.toList());
        Map brandAggsMap = new HashMap();
        brandAggsMap.put("name","品牌");
        brandAggsMap.put("field", "brand_name");
        brandAggsMap.put("content", brandAggsList);
        List aggsList = new ArrayList();
        aggsList.add(brandAggsMap);
        aggsList.add(catAggsMap);
        //设置过滤条件
        searchResult.setAggs(aggsList);
        return searchResult;
    }


}
