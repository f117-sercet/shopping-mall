package com.dsc.sso.service.impl;

import com.dsc.common.jedis.JedisClient;
import com.dsc.mall.manager.dto.DtoUtil;
import com.dsc.mall.manager.dto.front.CartProduct;
import com.dsc.mall.manager.mapper.TbItemMapper;
import com.dsc.mall.manager.pojo.TbItem;
import com.dsc.sso.service.CartService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车实现类
 * @author 60221
 */
public class CartServcieImpl implements CartService {

    private final static Logger log= LoggerFactory.getLogger(CartService.class);

    @Autowired
    private JedisClient jedisClient;
    @Value("${CART_PRE}")
    private String CART_PRE;
    @Autowired
    private TbItemMapper itemMapper;
    @Override
    public int addCart(long userId, long itemId, int num) {
        //hash:"key:用户id" field:"商品id" value:"商品信息"
        Boolean hexists = jedisClient.hexists(CART_PRE + ":" + userId, itemId + "");
        //如果存在数量相加
        if (hexists) {
            String json = jedisClient.hget(CART_PRE + ":" + userId, itemId + "");
            if (json != null) {
                CartProduct cartProduct = new Gson().fromJson(json, CartProduct.class);
                cartProduct.setProductNum(cartProduct.getProductNum() + num);
                jedisClient.hset(CART_PRE + ":" + userId, itemId + "", new Gson().toJson(cartProduct));
            } else {
                return 0;
            }

            return 1;
        }
        //如果不存在，根据商品id取商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        if (item == null) {
            return 0;
        }
        CartProduct cartProduct = DtoUtil.TbItem2CartProduct(item);
        cartProduct.setProductNum((long) num);
        cartProduct.setChecked("1");
        jedisClient.hset(CART_PRE + ":" + userId, itemId + "", new Gson().toJson(cartProduct));
        return 1;
    }

    @Override
    public List<CartProduct> getCartList(long userId) {
        List<String> jsonList = jedisClient.hvals(CART_PRE + ":" + userId);
        List<CartProduct> list = new ArrayList<>();
        for (String json : jsonList) {
            CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
            list.add(cartProduct);
        }
        return list;
    }

    @Override
    public int updateCartNum(long userId, long itemId, int num, String checked) {
        return 0;
    }

    @Override
    public int checkAll(long userId, String checkd) {
        return 0;
    }

    @Override
    public int deleteCartItem(long userId, long itemId) {
        return 0;
    }

    @Override
    public int delChecked(long userId) {
        return 0;
    }
}
