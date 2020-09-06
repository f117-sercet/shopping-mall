package com.dsc.supergo.manager.service;

import com.dsc.supergo.base.service.BaseService;
import com.dsc.supergo.pojo.Item;

import java.util.List;

public interface ItemService extends BaseService<Item> {
    public List<Item> skuList(Long goodsId);
    public int getItemStock(long itemId);
}
