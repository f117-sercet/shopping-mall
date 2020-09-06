package com.dsc.supergo.manager.service.impl;


import com.dsc.supergo.base.service.BaseServiceImpl;
import com.dsc.supergo.manager.service.ItemCatService;
import com.dsc.supergo.mapper.ItemcatMapper;
import com.dsc.supergo.pojo.Itemcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<Itemcat> implements ItemCatService {
    @Autowired
    private ItemcatMapper itemcatMapper;
    //@Autowired
    //private StringRedisTemplate redisTemplate;

    @Override
    public List<Itemcat> getItemCatList() {
        //查询分类类别
        List<Itemcat> list = getItems(0);
        return list;
    }

    private List<Itemcat> getItems(long parentId) {
        //根据parentId查询分类列表
        Itemcat query = new Itemcat();
        query.setParentId(parentId);
        List<Itemcat> list = this.findByWhere(query);
        //判断分类列表是否有数据
        if (list != null && list.size() > 0) {
            //如果有数据再次根据分类id查询分类列表（使用递归）
            list.forEach(c->c.setChildren(getItems(c.getId())));
        } else {
            //如果没有数据，直接返回null
            return null;
        }
        //最后返回分类列表
        return list;
    }
}
