package com.dsc.sso.service;

import com.dsc.mall.manager.pojo.TbAddress;

import java.util.List;

/**
 * 地址类接口
 * @author dsc
 */
public interface AddressService {
    /**
     * 获取地址
     * @param userId
     * @return
     */
    List<TbAddress> getAddressList(Long userId);
}
