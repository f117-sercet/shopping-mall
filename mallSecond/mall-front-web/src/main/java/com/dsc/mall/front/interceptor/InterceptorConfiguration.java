package com.dsc.mall.front.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 * @author dsc
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private LimitRaterInterceptor limitRaterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册拦截器
        InterceptorRegistration ir=registry.addInterceptor(limitRaterInterceptor);

        //配置拦截器路径
        ir.addPathPatterns("/**");
    }
}
