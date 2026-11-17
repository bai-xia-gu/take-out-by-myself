package com.baixiagu.takeout.merchant.controller;

import com.baixiagu.takeout.auth.LoginUser;
import com.baixiagu.takeout.auth.UserContext;
import com.baixiagu.takeout.common.Result;
import com.baixiagu.takeout.merchant.service.AdminAuthService;
import com.baixiagu.takeout.merchant.vo.AdminLoginVO;
import com.baixiagu.takeout.merchant.vo.AdminProfileVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家端-店铺：GET /admin/profile（需商家登录）
 */
@Tag(name = "商家端-店铺")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor // 无参构造函数
public class AdminProfileController {

    private final AdminAuthService adminAuthService;

    public Result<AdminProfileVO> profile(){
        LoginUser loginUser = UserContext.require();
        return Result.ok(adminAuthService.profile(loginUser.id()));
    }

}
