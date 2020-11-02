package com.dsc.search.search;

import com.dsc.mall.manager.dto.front.SearchResult;

/**
 * 搜索Service实现
 * @author
 */
public interface SearchService {
    /**
     * ES商品搜索
     * @param keyword
     * @param page
     * @param size
     * @param sort
     * @param priceGt
     * @param priceLte
     * @return
     */
    SearchResult search(String keyword, int page, int size, String sort, int priceGt, int priceLte);

    /**
     * restful搜索接口
     * @param key
     * @return
     */
    String quickSearch(String key);
}
