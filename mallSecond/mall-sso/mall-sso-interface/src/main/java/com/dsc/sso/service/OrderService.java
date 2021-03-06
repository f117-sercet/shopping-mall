package com.dsc.sso.service;

import com.dsc.mall.manager.dto.front.Order;
import com.dsc.mall.manager.dto.front.OrderInfo;
import com.dsc.mall.manager.dto.front.PageOrder;
import com.dsc.mall.manager.pojo.TbThanks;

/**
 * 订单
 * @author 60221
 */

public interface OrderService {
    /**
     * 分页获得用户订单
     * @param userId
     * @param page
     * @param size
     * @return
     */
    PageOrder getOrderList(Long userId, int page, int size);

    /**
     * 获得单个订单
     * @param orderId
     * @return
     */
    Order getOrder(Long orderId);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    int cancelOrder(Long orderId);

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    Long createOrder(OrderInfo orderInfo);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int delOrder(Long orderId);

    /**
     * 订单支付 生成捐赠数据
     * @param tbThanks
     * @return
     */
    int payOrder(TbThanks tbThanks);
}
