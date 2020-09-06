package com.dsc.supergo.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题过滤器
 * @author dsc
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        //添加Cors跨域配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许跨域访问的客户端域
        config.addAllowedOrigin("*");
        //允许请求带有验证信息
        config.setAllowCredentials(true);
        //允许客户端跨域访问携带请求头
        config.addAllowedHeader("*");
        //允许客户端跨域访问请求方式
        config.addAllowedMethod("*");

        //添加映射路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);

        CorsFilter corsFilter = new CorsFilter(configurationSource);

        return corsFilter;
    }

}
