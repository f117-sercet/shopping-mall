package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author dsc
 */
    @SpringBootApplication
    @EnableEurekaClient
    @EnableFeignClients("com.dsc.supergo.manager.feign")
    public class PortalApplication {
        public static void main(String[] args) {
            SpringApplication.run(PortalApplication.class, args);
        }
    }


