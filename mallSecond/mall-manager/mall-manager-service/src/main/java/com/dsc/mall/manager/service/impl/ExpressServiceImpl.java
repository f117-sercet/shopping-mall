package com.dsc.mall.manager.service.impl;

import com.dsc.mall.manager.mapper.TbExpressMapper;
import com.dsc.mall.manager.pojo.TbExpress;
import com.dsc.mall.manager.pojo.TbExpressExample;
import com.dsc.mall.mapper.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 *订单
 * @author 60221
 */
public class ExpressServiceImpl implements ExpressService {
    @Autowired
    private TbExpressMapper tbExpressMapper;

    @Override
    public List<TbExpress> getExpressList() {
        TbExpressExample example=new TbExpressExample();
        example.setOrderByClause("sort_order asc");
        return tbExpressMapper.selectByExample(example);
    }

    @Override
    public int addExpress(TbExpress tbExpress) {
        tbExpress.setCreated(new Date());

        return 1;
    }

    @Override
    public int updateExpress(TbExpress tbExpress) {
       tbExpress.setCreated(new Date());
       tbExpressMapper.insert(tbExpress);
        return 1;
    }

    @Override
    public int delExpress(int id) {
        tbExpressMapper.deleteByPrimaryKey(id);
        return 1;
    }
}
