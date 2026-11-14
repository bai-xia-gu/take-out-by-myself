package com.baixiagu.takeout.shop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 店铺列表/详情返回对象
 */
@Data
public class ShopVO {

    private String shopId;
    private String name;
    private String coverUrl;
    private String logoUrl;
    private BigDecimal rating;
    private int monthSales;
    private int minOrderAmount;
    private int deliveryFee;
    private int estimatedMinutes;
    private String businessStatus;
    private String nextOpenTime;
    private String categoryId;
    private String categoryName;
    private String notice;
}
