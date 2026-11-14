package com.baixiagu.takeout.auth;


import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.ExpiredJwtException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtService {

    private static final String CLAIM_ROLE = "role";
    private static final String CLAIM_SHOP_ID = "shopId";

    private final JwtProperties properties;
    private final SecretKey key;

    public JwtService(JwtProperties properties){
        this.properties = properties;
        this.key = Keys.hmacShaKeyFor(properties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /* 签发商家 token：sub=店长ID，role=MERCHANT，shopId=店铺ID */
    public String issueMerchant(Long merchantId,Long shopId){
        Date now = new Date();
        Date exp = new Date(now.getTime()+properties.getExpireMillis()*1000);

        return Jwts.builder() // JWT的组成部分
                .subject(String.valueOf(merchantId))  // 店长ID
                .claim(CLAIM_ROLE, "MERCHANT")  // 角色
                .claim(CLAIM_SHOP_ID,String.valueOf(shopId))  // 店铺ID
                .issuedAt(now)  // 签发时间
                .expiration(exp)  // 过期时间
                .signWith(key)  // 签名
                .compact();
    }

    /* 解析并校验；过期抛 40102，其余非法抛 40101 */
    public LoginUser parse(String token){
        try {
            Claims claims = Jwts.parser()// 解析 JWT
                    .verifyWith(key) // 校验签名
                    .build() // 构建解析器
                    .parseSignedClaims(token)  // 为什么需要解析Claims？因为Claims包含了payload，而payload包含了用户信息
                    .getPayload(); // 获取 payload
            Long id = Long.valueOf(claims.getSubject());
            String role = claims.get(CLAIM_ROLE,String.class);
            String shopIdText = claims.get(CLAIM_SHOP_ID,String.class);
            Long shopId = shopIdText == null ? null:Long.valueOf(shopIdText);

            return new LoginUser(id,role,shopId);

        } catch (ExpiredJwtException e) {
            throw new BizException(ErrorCode.TOKEN_EXPIRED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new BizException(ErrorCode.UNAUTHORIZED);
        }
    }
}
