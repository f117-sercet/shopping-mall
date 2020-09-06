package com.dsc.supergo.portal.controller;

import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.manager.feign.ApiItemCatFeign;
import com.dsc.supergo.pojo.Itemcat;
import com.dsc.supergo.user.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCatController {

    @Autowired
    private ApiItemCatFeign apiItemCatFeign;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/categorys/categorys")
    public HttpResult getItemCatList() {
        //从redis中查询数据
        String json = redisTemplate.opsForValue().get("portal-itemcats");
        //如果redis缓存存在，直接使用jackson反序列化成集合返回
        if (StringUtils.isNotBlank(json)) {
            List<Itemcat> list = JsonUtils.jsonToList(json, Itemcat.class);
            return HttpResult.ok(list);
        }
        //缓存中没有数据，查询数据库
        List<Itemcat> list = apiItemCatFeign.getItemCatList();
        redisTemplate.opsForValue().set("portal-itemcats", JsonUtils.objectToJson(list));

        return HttpResult.ok(list);
    }

}
