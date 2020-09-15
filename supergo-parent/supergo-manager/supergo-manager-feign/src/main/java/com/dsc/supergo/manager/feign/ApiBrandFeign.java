package com.dsc.supergo.manager.feign;
import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.pojo.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("supergo-manager1")
//@RequestMapping("/brand") //不要在feign接口上使用此注解，否则会被处理器映射器扫描
public interface ApiBrandFeign {

    @RequestMapping("/brand/{brandId}")
    public Brand getBrandById(@PathVariable("brandId") long brandId);

    @PostMapping("/brand/query/{page}/{size}")
    public HttpResult getBrandList(@PathVariable("page") int page,
                                   @PathVariable("size") int size,
                                   @RequestBody(required = false) Brand brand);

}
