package com.dsc.supergo.manager.feign;
import com.dsc.supergo.pojo.Goods;
import com.dsc.supergo.pojo.Goodsdesc;
import com.dsc.supergo.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface ApiGoodsFeign {

    @GetMapping("/goods/{goodsId}")
    public Goods getGoodsById(@PathVariable("goodsId") long goodsId);

    @GetMapping("/goods/desc/{goodsId}")
    public Goodsdesc getGoodsDescById(@PathVariable("goodsId") long goodsId);

    @GetMapping("/goods/item/{goodsId}")
    public List<Item> getItemList(@PathVariable("goodsId") long goodsId);

}
