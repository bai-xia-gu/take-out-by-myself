package com.baixiagu.takeout.auth;

/**
 * 当前请求的登录身份，由 JwtService 解析 token 后构造
 * role 取值 USER / MERCHANT；shopId 仅商家端 token 有值
 */
public record LoginUser(Long id, String role, Long shopId) {

    /**
     * 是否为商家端
     */
    public boolean isMerchant(){
        return role.equals("MERCHANT");
    }


    /**
     * 是否为用户端
     */
    public boolean isUser(){
        return role.equals("USER");
    }
}
