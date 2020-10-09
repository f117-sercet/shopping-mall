package com.dsc.sso.service;

import com.dsc.mall.manager.dto.front.CartProduct;

import java.util.List;

/**
 * 购物车
 * @author 60221
 */
public interface CartService {
    /**
     * 添加
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    int addCart(long userId,long itemId,int num);

    /**
     * 获取
     * @param userId
     * @return
     */
  List<CartProduct> getCartList(long userId);
}
