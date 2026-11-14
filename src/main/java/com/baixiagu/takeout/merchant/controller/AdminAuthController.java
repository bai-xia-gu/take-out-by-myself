package com.baixiagu.takeout.merchant.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家端-认证：POST /admin/auth/login（免鉴权）
 */
@Tag(name = "商家端-认证")
@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {

    // TODO 按接口文档 2.4 实现登录
}
