package com.dsc.mall.front.limit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.ZParams;

import java.util.UUID;

/**
 * Redis线路响应
 * @author dsc
 */
public class RedisRaterLimiter {
    final static Logger log= LoggerFactory.getLogger(RedisRaterLimiter.class);

    @Autowired
    private JedisPool jedisPool;

    private static final String BUCKET = "BUCKET_";
    private static final String BUCKET_COUNT = "BUCKET_COUNT";
    private static final String BUCKET_MONITOR = "BUCKET_MONITOR_";

    public String acquireTokenForBucket(String point,int limit,long timeout){
        Jedis jedis = jedisPool.getResource();

try {
    //UUID令牌
    String token= UUID.randomUUID().toString();
    long now=System.currentTimeMillis();
    //开启事务
    Transaction transaction=jedis.multi();

    //删除信号量 移除有序集合中指定区间内的所有成员 ZREMRANGEBYSCORE key min max
     transaction.zremrangeByScore((BUCKET_MONITOR+point).getBytes(),"-inf".getBytes(),String.valueOf(now-timeout).getBytes());
     //为每个有序集合分别指定一个乘法因子(默认设置为1)每个成员的score值在传递给聚合函数之前都要先乘以该因子
    ZParams params=new ZParams();
    params.weightsByDouble(1.0,0.0);
    //计算给定的一个或者多个有序的交集
    transaction.zinterstore(BUCKET + point, params, BUCKET + point, BUCKET_MONITOR + point);

   //计数器自增
   transaction.incr(BUCKET_COUNT);

}catch (Exception e){
    log.error("限流出售"+e.toString());
}finally {
    if (jedis!=null){
        jedis.close();
    }

}
        return null;

    }
}
