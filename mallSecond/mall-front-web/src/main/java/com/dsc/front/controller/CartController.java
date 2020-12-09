package com.dsc.front.controller;

import com.dsc.common.pojo.Result;
import com.dsc.common.utils.ResultUtil;
import com.dsc.mall.manager.dto.front.Cart;
import com.dsc.sso.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dsc
 */
@RestController
@Api(description = "购物车")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/member/addCart",method = RequestMethod.POST)
    @ApiOperation(value = "添加购物车")
    public Result<Object> addCart(@RequestBody Cart cart)
    {
        int  result=cartService.addCart(cart.getUserId(),cart.getProductId(),cart.getProductNum());
       return  new ResultUtil<Object>().setData(result);
    }
}
