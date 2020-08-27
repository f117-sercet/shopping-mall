package com.dsc.mall.manager.service.impl;

import com.dsc.common.execetion.MallException;
import com.dsc.common.pojo.ZtreeNode;
import com.dsc.mall.manager.dto.DtoUtil;
import com.dsc.mall.manager.mapper.TbItemCatMapper;
import com.dsc.mall.manager.pojo.TbItem;
import com.dsc.mall.manager.pojo.TbItemCat;
import com.dsc.mall.manager.pojo.TbItemCatExample;
import com.dsc.mall.mapper.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类实现类
 * @author 60221
 */
public class ItemCatServiceImpl implements ItemCatService {
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public TbItemCat getItemCatById(Long id) {
        TbItemCat tbItemCat=tbItemCatMapper.selectByPrimaryKey(id);
        if (tbItemCat==null){
            throw new MallException("通过id获取商品分类失败");
        }
        return tbItemCat;
    }

    @Override
    public List<ZtreeNode> getItemCatList(int parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();
        //排序
        example.setOrderByClause("sort_order");
        //条件查询
        criteria.andParentIdEqualTo(new Long(parentId));
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);

        //转换成ZtreeNode
        List<ZtreeNode> resultList=new ArrayList<>();

        for(TbItemCat tbItemCat:list){

            ZtreeNode node= DtoUtil.TbItemCat2ZTreeNode(tbItemCat);

            resultList.add(node);
        }

        return resultList;
    }

    @Override
    public int addItemCat(TbItemCat tbItemCat) {
        return 0;
    }

    @Override
    public int updateItemCat(TbItemCat tbItemCat) {
        return 0;
    }

    @Override
    public void deleteItemCat(Long id) {

    }

    @Override
    public void deleteZTree(Long id) {

    }
}
