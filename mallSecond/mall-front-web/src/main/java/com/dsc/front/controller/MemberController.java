package com.dsc.front.controller;

import cn.hutool.core.collection.CollUtil;
import com.dsc.common.jedis.JedisClient;
import com.dsc.common.pojo.GeetInit;
import com.dsc.common.utils.GeetestLib;
import com.dsc.mall.mapper.service.MemberService;
import com.dsc.sso.service.LoginService;
import com.dsc.sso.service.RegisterService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

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

    public String geetesrInit(HttpServletRequest request){
        GeetestLib gtSdk =new GeetestLib(GeetestLib.id,GeetestLib.key,GeetestLib.newfailback);

        String reStr ="{}";

        //自定义参数，可选择添加
        HashMap<String,String> param = new HashMap<>();

        //进行验证处理
        int gtServerStatus =gtSdk.preProcess(param);

        //将服务器状态设置到redis中

        String key = UUID.randomUUID().toString();
        jedisClient.set(key,gtServerStatus+"");
        jedisClient.expire(key,360);
        reStr = gtSdk.getResponseStr();
        GeetInit geetInit = new Gson().fromJson(reStr,GeetInit.class);
        geetInit.setStatusKey(key);
        return new Gson().toJson(geetInit);

    }

}
