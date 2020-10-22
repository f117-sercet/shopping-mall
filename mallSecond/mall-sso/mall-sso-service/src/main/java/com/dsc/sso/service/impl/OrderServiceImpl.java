package com.dsc.sso.service.impl;

import com.dsc.mall.manager.dto.front.Order;
import com.dsc.mall.manager.dto.front.OrderInfo;
import com.dsc.mall.manager.dto.front.PageOrder;
import com.dsc.mall.manager.mapper.TbMemberMapper;
import com.dsc.mall.manager.mapper.TbOrderMapper;
import com.dsc.mall.manager.pojo.TbThanks;
import com.dsc.sso.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单Service实现类
 * @author 60221
 */
public class OrderServiceImpl implements OrderService {

    private final static Logger log= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    /**
     * 用户
     */
    private TbMemberMapper tbMemberMapper;
    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public PageOrder getOrderList(Long userId, int page, int size) {
        return null;
    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public int cancelOrder(Long orderId) {
        return 0;
    }

    @Override
    public Long createOrder(OrderInfo orderInfo) {
        return null;
    }

    @Override
    public int delOrder(Long orderId) {
        return 0;
    }

    @Override
    public int payOrder(TbThanks tbThanks) {
        return 0;
    }
}
