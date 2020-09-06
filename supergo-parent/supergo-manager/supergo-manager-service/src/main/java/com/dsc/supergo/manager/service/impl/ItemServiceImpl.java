package com.dsc.supergo.manager.service.impl;
import com.dsc.supergo.base.service.BaseServiceImpl;
import com.dsc.supergo.manager.service.ItemService;
import com.dsc.supergo.mapper.ItemMapper;
import com.dsc.supergo.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    /****
     * 查询对应的SKU列表
     * @param goodsId
     * @return
     */
    @Override
    public List<Item> skuList(Long goodsId) {
        //select * from tb_item where goods_id=? and status=1 order by is_default desc
        Example example = new Example(Item.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", goodsId);
        criteria.andEqualTo("status", "1");
        //设置排序
        example.orderBy("isDefault").desc();
        return itemMapper.selectByExample(example);
    }

    @Override
    public int getItemStock(long itemId) {
        Item item = new Item();
        item.setId(itemId);
        Item result = itemMapper.selectOne(item);
        return result.getStockCount();
    }

}