package com.dsc.mall.front.interceptor;

import com.dsc.mall.front.limit.RedisRaterLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限流拦截器
 * @author dsc
 */
public class LimitRaterInterceptor extends HandlerInterceptorAdapter {

     @Value("true")
    private boolean rateLimitEnable;

     @Value("20")
    private Integer limit;

     @Value("1000")
    private Integer timeout;

     @Autowired
    private RedisRaterLimiter redisRaterLimiter;
    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）
     * 第三个参数为响应的处理器，即controller
     * 返回true，表示继续流程，调用下一个拦截器或者处理器
     * 返回false，表示流程中断，通过response产生响应
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
}
}
