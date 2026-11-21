package com.baixiagu.takeout.merchant.controller;

import com.baixiagu.takeout.common.Result;
import com.baixiagu.takeout.merchant.dto.AdminLoginRequest;
import com.baixiagu.takeout.merchant.service.AdminAuthService;
import com.baixiagu.takeout.merchant.vo.AdminLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家端-认证：POST /admin/auth/login（免鉴权）
 */
@Tag(name = "商家端-认证")
@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor // 无参构造函数
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

    @Operation(summary = "店长登录")
    @PostMapping("/login")
    public Result<AdminLoginVO>login(@Valid @RequestBody AdminLoginRequest request){
        return Result.ok(adminAuthService.login(request));
    }
}
