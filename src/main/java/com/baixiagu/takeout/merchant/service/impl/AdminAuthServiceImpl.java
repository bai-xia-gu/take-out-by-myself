package com.baixiagu.takeout.merchant.service.impl;

import com.baixiagu.takeout.merchant.service.AdminAuthService;
import org.springframework.stereotype.Service;

/**
 * 商家端认证业务实现
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    // TODO 注入 MerchantMapper / ShopMapper / JwtService / JwtProperties
    // TODO 登录失败统一抛 BizException(ErrorCode.UNAUTHORIZED, "账号或密码错误")
}
