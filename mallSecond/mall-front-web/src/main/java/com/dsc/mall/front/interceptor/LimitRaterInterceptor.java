package com.dsc.mall.front.interceptor;

import cn.hutool.core.util.StrUtil;
import com.dsc.common.annnotation.RateLimiter;
import com.dsc.common.constant.CommonConstant;
import com.dsc.common.execetion.MallException;
import com.dsc.common.utils.IPInfoUtil;
import com.dsc.mall.front.limit.RedisRaterLimiter;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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
        //IP限流 在线Demo所需 一秒10个请求
        String token1 = redisRaterLimiter.acquireTokenForBucket("mall" + IPInfoUtil.getIpAddr(request), 10, 10);
        if (StrUtil.isBlank(token1)) {
            throw new MallException("你他娘的点那么快干嘛");
        }

        if (rateLimitEnable) {
            String token2 = redisRaterLimiter.acquireTokenForBucket(CommonConstant.LIMIT_ALL, limit, timeout);
            if (StrUtil.isBlank(token2)) {
                throw new MallException("当前访问总人数较多啦，请稍后再试");
            }
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);

        if (rateLimiter != null) {
            int limit = rateLimiter.limit();
            int timeout = rateLimiter.timeout();
            String token3 = redisRaterLimiter.acquireTokenForBucket(method.getName(), limit, timeout);
            if (StrUtil.isBlank(token3)) {
                throw new MallException("当前访问人数太多啦，请稍后再试");
            }
        }
        return true;
    }
    /**
     * controller方法调用之后执行
     * 在DispatcherServlet进行视图返回渲染之前被调用
     * 此时可以通过modelAndView对模型数据进行处理或对视图进行处理
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 该方法用于进行资源清理工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


