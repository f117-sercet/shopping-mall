package com.dsc.supergo.sso.utils;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {

    private String key; //密钥

    private long ttl;   //过期时间

    /**
     * 生成JWT token
     */
    public String createToken(String id, String subject, String role) {
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + ttl * 1000));
        }
        return builder.compact();
    }

    /**
     * 解析JWT token
     */
    public Claims parseToken(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
