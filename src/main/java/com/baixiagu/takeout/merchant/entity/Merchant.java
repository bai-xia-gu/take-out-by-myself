package com.baixiagu.takeout.merchant.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店长实体，对应表 merchant（V4 迁移创建）
 */
@Data
public class Merchant {

    private Long id;
    private String account;
    private String passwordHash;
    private String salt;
    private String name;
    private Long shopId;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
