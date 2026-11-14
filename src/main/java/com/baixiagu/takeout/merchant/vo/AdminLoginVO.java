package com.baixiagu.takeout.merchant.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 店长登录返回 {token, expiresIn, merchantId, shopId, shopName}（接口文档 2.4）
 */
@Data
@Builder
public class AdminLoginVO {

    private String token;

    private Long expiresIn;
    private String merchantId;
    private String shopId;
    private String shopName;
}
