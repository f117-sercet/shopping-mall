package com.dsc.mall.manager.service.impl;
import com.dsc.common.constant.DictConstant;
import com.dsc.mall.manager.mapper.TbDictMapper;
import com.dsc.mall.manager.pojo.TbDict;
import com.dsc.mall.manager.pojo.TbDictExample;
import com.dsc.mall.mapper.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * service实现类
 * @author 60221
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    TbDictMapper tbDictMapper;
    @Override
    public List<TbDict> getDictList() {
        TbDictExample example=new TbDictExample();
        TbDictExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andTypeEqualTo(DictConstant.DICT_EXT);
        List<TbDict> list=tbDictMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<TbDict> getStopList() {
        TbDictExample example=new TbDictExample();
        TbDictExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andTypeEqualTo(DictConstant.DICT_STOP);
        List<TbDict> list = tbDictMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addDict(TbDict tbDict) {
        tbDictMapper.insert(tbDict);
        return 1;
    }

    @Override
    public int updateDict(TbDict tbDict) {
        tbDictMapper.updateByPrimaryKeySelective(tbDict);
        return 1;

    }

    @Override
    public int delDict(int id) {
        tbDictMapper.deleteByPrimaryKey(id);
        return 1;
    }
}
