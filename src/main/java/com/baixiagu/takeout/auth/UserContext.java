package com.baixiagu.takeout.auth;

import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;

/**
 * 当前请求的登录用户，从 Token 解析后放入 ThreadLocal
 */
public class UserContext {

    /* 用 ThreadLocal 让 Service 层拿到当前登录人，避免每个方法都传 token */
    private static final  ThreadLocal<LoginUser> HOLDER = new ThreadLocal<>();

    private UserContext(){}

    public static void set(LoginUser user){
        HOLDER.set(user); // 把当前登录人放入 ThreadLocal，避免每个方法都传 token
    }

    /* 取不到说明没登录/拦截器没生效，直接 40101 */
    public static LoginUser require(){
        LoginUser user = HOLDER.get();
        if(user == null){
            throw new BizException(ErrorCode.UNAUTHORIZED);
        }
        return user;
    }

    // 取不到说明没登录/拦截器没生效，直接 40101
    public static LoginUser get(){
        return HOLDER.get();
    }
    // 清除当前登录人，用于退出登录
    public static void clear() {
        HOLDER.remove();
    }
}
