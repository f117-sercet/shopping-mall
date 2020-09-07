package com.dsc.supergo.search.repository;


import com.dsc.supergo.search.eneity.GoodsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepostory extends ElasticsearchRepository<GoodsEntity, Long> {

}
