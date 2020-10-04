package com.dsc.mall.front.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收货地址Conyroller
 * @author dsc
 */
@RestController
@Api(description = "收货地址")
public class AddressController {

    @Autowired
    private AddressService addressService;
}
