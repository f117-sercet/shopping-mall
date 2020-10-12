package com.dsc.sso.service.impl;

import com.dsc.common.execetion.MallException;
import com.dsc.mall.manager.mapper.TbAddressMapper;
import com.dsc.mall.manager.pojo.TbAddress;
import com.dsc.mall.manager.pojo.TbAddressExample;
import com.dsc.sso.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址实现类
 * @author 60221
 */
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TbAddressMapper tbAddressMapper;
    @Override
    public List<TbAddress> getAddressList(Long userId) {

        List<TbAddress> list=new ArrayList<>();
        TbAddressExample example=new TbAddressExample();
        TbAddressExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        list=tbAddressMapper.selectByExample(example);
        if (list==null){
            throw new MallException("获取默认地址列表失败");
        }
        return null;
    }

    @Override
    public TbAddress getAddress(Long addressId) {
        return null;
    }

    @Override
    public int addAddress(TbAddress tbAddress) {
        return 0;
    }

    @Override
    public int updateAddress(TbAddress tbAddress) {
        return 0;
    }

    @Override
    public int delAddress(TbAddress tbAddress) {
        return 0;
    }
}
