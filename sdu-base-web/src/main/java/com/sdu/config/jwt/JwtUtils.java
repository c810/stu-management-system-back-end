package com.sdu.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Connor
 * @date 2022/11/4 21:55
 * Jwt工具类 token令牌
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt") // 读取配置文件中以jwt开头的部分
public class JwtUtils {
    // 颁发者
    private String issuer;
    // 密钥
    private String secret;
    // 过期时间(分钟)
    private int expiration;

    /**
     * 生成token
     * @param map: 自定义参数
     * @return 返回token
     */
    public String generateToken(Map<String, String> map) {
        // 设置令牌的过期时间
        // 获取日历实例
        Calendar instance = Calendar.getInstance();
        // 设置失效时间
        instance.add(Calendar.MINUTE, expiration);
        // 创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        // 设置自定义参数
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 生成token
        String token = builder.withIssuer(issuer) // 设置颁发者
                .withIssuedAt(new Date()) // 设置颁发时间
                .withExpiresAt(instance.getTime()) // 指定过期时间
                .sign(Algorithm.HMAC256(secret)); // 设置密钥
        return token;
    }

    /**
     * 验证token是否合法
     * @param token
     * @return
     */
    public boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public DecodedJWT jwtDecode(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token签名错误!");
        } catch (AlgorithmMismatchException e) {
            throw new RuntimeException("token算法不匹配!");
        } catch (TokenExpiredException e) {
            throw new RuntimeException("token过期!");
        } catch (Exception e) {
            throw new RuntimeException("token解析失败!");
        }
    }
}
