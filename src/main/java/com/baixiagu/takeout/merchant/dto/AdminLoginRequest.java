package com.baixiagu.takeout.merchant.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 店长登录入参 {account, password}（接口文档 2.4）
 */
@Data
public class AdminLoginRequest {

    @NotBlank(message = "不能为空")
    @Size(max = 32, message = "长度不能超过 32 ")
    private String account;

    @NotBlank(message = "不能为空")
    @Size(max = 64, message = "长度不能超过 64 ")
    private String password;
}
