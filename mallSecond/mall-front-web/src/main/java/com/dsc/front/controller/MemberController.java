package com.dsc.front.controller;

import com.dsc.common.jedis.JedisClient;
import com.dsc.mall.mapper.service.MemberService;
import com.dsc.sso.service.LoginService;
import com.dsc.sso.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dsc
 */
@RestController
@Api(description = "会员注册登录")
public class MemberController {
    private final static Logger log= LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private JedisClient jedisClient;

}
