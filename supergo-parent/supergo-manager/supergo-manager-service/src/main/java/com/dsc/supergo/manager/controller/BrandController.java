package com.dsc.supergo.manager.controller;

import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.manager.service.BrandService;
import com.dsc.supergo.page.PageResult;
import com.dsc.supergo.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/{brandId}")
    public Brand getBrandById(@PathVariable long brandId) {
        Brand brand = brandService.findOne(brandId);
        return brand;
    }

    @PostMapping("/query/{page}/{size}")
    public HttpResult getBrandList(@PathVariable int page,
                                   @PathVariable int size,
                                   @RequestBody(required = false) Brand brand) {
        PageResult result = brandService.findPage(page, size, brand);
        return HttpResult.ok(result);
    }

}
