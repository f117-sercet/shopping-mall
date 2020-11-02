package com.dsc.search.search;

import com.dsc.mall.manager.dto.EsInfo;

/**
 * 搜索
 * @author dsc
 */
public interface SearchItemService {
    /**
     * 同步索引
     * @return
     */
    int importAllItems();

    /**
     * 获取ES基本信息
     * @return
     */
    EsInfo getEsInfo();
}
