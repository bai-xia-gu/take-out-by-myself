package com.baixiagu.takeout.auth;

import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * 登录拦截器，校验 Authorization 头
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;

    public AuthInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /*
     * 处理请求前，校验 Authorization 头
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       if("OPTIONS".equals(request.getMethod())){
           return true;
       }

       String header = request.getHeader("Authorization");
       if(header == null || !header.startsWith(BEARER_PREFIX)){//BEARER_PREFIX 作用是校验 token 是否以 Bearer 开头
           throw new BizException(ErrorCode.UNAUTHORIZED);
       }
       LoginUser user = jwtService.parse(header.substring(BEARER_PREFIX.length()).trim()); // 解析 token 并校验获取用户信息

        // /admin/** 必须是 MERCHANT，用户 token 调商家接口返回 403 + 40103
        String path = request.getRequestURI().substring(request.getContextPath().length()); // 获取请求路径，去掉上下文路径
        if(path.startsWith("/admin")&&!user.isMerchant()){
            throw new BizException(ErrorCode.ROLE_MISMATCH);
        }
        UserContext.set(user);
        return true;
    }

    // 处理完成后，清除 UserContext 中的用户信息
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        UserContext.clear();
    }
}
