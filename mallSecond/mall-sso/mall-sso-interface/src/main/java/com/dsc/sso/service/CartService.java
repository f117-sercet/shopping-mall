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

    /**
     * 更新
     * @param userId
     * @param itemId
     * @param num
     * @param checked
     * @return
     */
  int updateCartNum(long userId,long itemId,int num,String checked);

    /**
     * 全选反选
     * @param userId
     * @param checkd
     * @return
     */
  int checkAll(long userId,String checkd);

    /**
     * 删除多个
     *
     * @param userId
     * @param itemId
     * @return
     */
   int deleteCartItem(long userId,long itemId);

    /**
     * 删除全勾选的
     * @param userId
     * @return
     */
   int delChecked(long userId);
}
