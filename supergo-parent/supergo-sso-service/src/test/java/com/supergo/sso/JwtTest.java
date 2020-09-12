package com.supergo.sso;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {

    @Test
    public void createJwt() {
        String token = Jwts.builder()
                .setId("123")
                .setSubject("admin")
                //签发时间
                .setIssuedAt(new Date())
                //签名
                .signWith(SignatureAlgorithm.HS256, "abc123")
                .compact();
        System.out.println(token);
    }

    @Test
    public void parseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Nzk5MTg3M30.MhUVaSEbk4DdSbKG2gRLAbhTRimI6iQhjJK16hHKCUQ";
        Jws<Claims> jws = Jwts.parser().setSigningKey("abc123").parseClaimsJws(token);
        Claims claims = jws.getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
    }

    @Test
    public void testJwtExpire() {
        //过期时间，当前时间+10s
        long exp = System.currentTimeMillis() + 10 * 1000;

        String token = Jwts.builder()
                .setId("123456")
                .setSubject("hello jwt")
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS256, "thisispassword")
                .compact();
        System.out.println(token);

        Jws<Claims> jws = Jwts.parser().setSigningKey("thisispassword").parseClaimsJws(token);
        Claims body = jws.getBody();
        System.out.println(body.getId());
        System.out.println(body.getSubject());
        System.out.println(body.getIssuedAt());
        System.out.println(body.getExpiration());
        for (int i = 10; i > 0; i--) {
            System.out.println("waiting for " + i + " seconds....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //再次解析Jwt
        jws = Jwts.parser().setSigningKey("thisispassword").parseClaimsJws(token);
        body = jws.getBody();
        System.out.println(body.getId());
        System.out.println(body.getSubject());
        System.out.println(body.getIssuedAt());
        System.out.println(body.getExpiration());
    }

    @Test
    public void testClaims() {
        //过期时间，当前时间+10s
        long exp = System.currentTimeMillis() + 10 * 1000;

        String token = Jwts.builder()
                .setId("123456")
                .setSubject("hello jwt")
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(exp))
                .claim("key1", 123456)
                .claim("key2", "abcdef")
                .claim("key3", "lxs")
                .signWith(SignatureAlgorithm.HS256, "thisispassword")
                .compact();
        System.out.println(token);

        Jws<Claims> jws = Jwts.parser().setSigningKey("thisispassword").parseClaimsJws(token);
        Claims body = jws.getBody();
        System.out.println(body.getId());
        System.out.println(body.getSubject());
        System.out.println(body.getIssuedAt());
        System.out.println(body.getExpiration());
        System.out.println(body.get("key1"));
        System.out.println(body.get("key2"));
        System.out.println(body.get("key3"));
    }

}
