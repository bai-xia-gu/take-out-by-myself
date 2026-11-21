package com.baixiagu.takeout.merchant.controller;


import com.baixiagu.takeout.auth.UserContext;
import com.baixiagu.takeout.common.Result;
import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.service.AdminCategoryService;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "商家端-分类")
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    /**
     * 获取当前登录用户的店铺ID
     * @return 店铺ID
     */
    private Long shopId(){
        return UserContext.require().shopId();
    }

    /**
     * 获取店铺下的分类列表
     * @return 分类列表
     */
    public Result<List<AdminCategoryVO>> list(){
        return Result.ok(adminCategoryService.list(shopId()));
    }

    /**
     * 创建分类
     * @param request 分类创建请求
     * @return 创建的分类VO
     */
    public Result<AdminCategoryVO> create(@Valid @RequestBody CategorySaveRequest request){
        return Result.ok(adminCategoryService.create(shopId(), request));
    }

}
