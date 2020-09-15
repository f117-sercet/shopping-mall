package com.dsc.supergo.manager.controller;

import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.pojo.Brand;
import com.dsc.supergo.manager.feign.ApiBrandFeign;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
//允许跨域请求，在zuul中全局配置后，应该删除次注解
//@CrossOrigin
@Api(value = "品牌控制器", protocols = "http", description = "品牌控制器")
public class BrandController {
    @Autowired
    private ApiBrandFeign brandFeign;

    @ApiOperation(value = "根据ID查询品牌", notes = "根据ID查询品牌")
    @ApiImplicitParam(paramType = "path", name = "brandId", value = "商品ID")
    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    public HttpResult getBrandById(@PathVariable long brandId) {
        Brand brand = brandFeign.getBrandById(brandId);
        return HttpResult.ok(brand);
    }

    @ApiOperation(value = "品牌分页查询",notes = "接受分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "条件分页查询品牌成功"),
            @ApiResponse(code = 500, message = "条件分页查询品牌失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "代表此操作无权限访问"),
            @ApiResponse(code = 400, message = "表示请求参数错误"),
    })
    @PostMapping("/query/{page}/{size}")
    public HttpResult getBrandList(@PathVariable int page,
                                   @PathVariable int size,
                                   @RequestBody(required = false) Brand brand) {
        HttpResult httpResult = brandFeign.getBrandList(page, size, brand);
        return httpResult;
    }
}
