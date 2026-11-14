package com.baixiagu.takeout.merchant.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家端-店铺：GET /admin/profile（需商家登录）
 */
@Tag(name = "商家端-店铺")
@RestController
@RequestMapping("/admin")
public class AdminProfileController {

    // TODO 按接口文档 2.5 实现，身份取 UserContext.require()
}
