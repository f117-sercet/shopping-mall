package com.dsc.supergo.manager.service;


import com.dsc.supergo.base.service.BaseService;
import com.dsc.supergo.pojo.Itemcat;

import java.util.List;

public interface ItemCatService extends BaseService<Itemcat> {
    List<Itemcat> getItemCatList();
}
