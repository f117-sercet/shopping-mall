package com.dsc.supergo.manager.feign;


import com.dsc.supergo.pojo.Itemcat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient
public interface ApiItemCatFeign {

    @RequestMapping("/cat/category")
    public List<Itemcat> getItemCatList();

    @RequestMapping("/itemcat/category/{categoryId}")
    public Itemcat getItemCatById(@PathVariable("categoryId") long categoryId);
}
