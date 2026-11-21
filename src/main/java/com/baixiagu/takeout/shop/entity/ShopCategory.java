package com.baixiagu.takeout.shop.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店铺与分类关联
 */
@Data
public class ShopCategory {

    private Long id;
    private Long shopId;
    private String name;
    private Integer sort;
    private LocalDateTime createTime;
}
