package com.baixiagu.takeout.merchant.vo;

import com.baixiagu.takeout.shop.vo.ShopVO;
import lombok.Data;
import lombok.Builder;

/**
 * 店长与店铺信息返回 {merchantId, account, shop}（接口文档 2.5）
 */
@Data
@Builder
public class AdminProfileVO {

    private String merchantId;
    private String account;
    private ShopVO shop;
}
