package com.baixiagu.takeout.merchant.service;

import com.baixiagu.takeout.merchant.dto.AdminLoginRequest;
import com.baixiagu.takeout.merchant.vo.AdminLoginVO;
import com.baixiagu.takeout.merchant.vo.AdminProfileVO;

/**
 * 商家端认证业务接口
 */
public interface AdminAuthService {

    /**
     * 店长登录
     */
    AdminLoginVO login(AdminLoginRequest request);

    /**
     * 店长登录查询店铺信息
     */
    AdminProfileVO profile(Long merchantId);
}
