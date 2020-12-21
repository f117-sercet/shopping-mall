package com.dsc.front.controller;

import com.dsc.common.pojo.Result;
import com.dsc.common.utils.ResultUtil;
import com.dsc.mall.manager.dto.front.Order;
import com.dsc.mall.manager.dto.front.PageOrder;
import com.dsc.sso.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  dsc
 * 订单类controller
 */
@RestController
@Api(description = "订单")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/member/orderList", method = RequestMethod.GET)
    @ApiOperation(value = "获得用户所有订单")
    public Result<PageOrder> getOrderList(String userId,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "5") int size) {

        PageOrder pageOrder = orderService.getOrderList(Long.valueOf(userId), page, size);
        return new ResultUtil<PageOrder>().setData(pageOrder);
    }
    @RequestMapping(value = "/member/orderDetail",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取订单")
    public Result<Order> getOrder(String orderId){

        Order order = orderService.getOrder(Long.valueOf(orderId));
        return new ResultUtil<Order>().setData(order);
    }

}
