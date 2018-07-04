package com.gxjtkyy.standardcloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * JWT工具类
 * @Package com.gxjtkyy.quality.utils
 * @Author lizhenhua
 * @Date 2018/6/1 10:49
 */
public class JwtUtil {

    /**
     * 解析JWT
     * @param token
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String token, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(token).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            return null;
        }
    }



    /**
     * 构建JWT
     * @param userId 用户ID
     * @param userName  用户姓名
     * @param userType  用户类型
     * @param audience
     * @param issuer    签发者
     * @param ttlMillis 超时时间
     * @param base64Security    密钥
     * @return
     */
    public static String createJWT(String userId,  String userName,String userType,String clientIp, String audience, String issuer, long ttlMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                .claim("userName", userName)
                .claim("userType", userType)
                .claim("userId", userId)
                .claim("clientIp", clientIp)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

}
