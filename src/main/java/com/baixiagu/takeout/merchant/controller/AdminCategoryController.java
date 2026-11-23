package com.baixiagu.takeout.merchant.controller;


import com.baixiagu.takeout.auth.UserContext;
import com.baixiagu.takeout.common.Result;
import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.dto.CategorySortRequest;
import com.baixiagu.takeout.merchant.service.AdminCategoryService;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public Result<List<AdminCategoryVO>> list(){
        return Result.ok(adminCategoryService.list(shopId()));
    }

    /**
     * 创建分类
     * @param request 分类创建请求
     * @return 创建的分类VO
     */
    @PostMapping
    public Result<AdminCategoryVO> create(@Valid @RequestBody CategorySaveRequest request){
        return Result.ok(adminCategoryService.create(shopId(), request));
    }

    /**
     * 更新分类名称
     * @param categoryId 分类ID
     * @param request 分类更新请求
     * @return 更新结果
     */
    @PutMapping("/{categoryId}")
    public Result<Void> rename(@PathVariable Long categoryId,
                               @Valid @RequestBody CategorySaveRequest request){
        adminCategoryService.rename(shopId(),categoryId,request);
        return Result.ok();
    }

    @PatchMapping("/{categoryId}/sort")
    public Result<Void>updateSort(@PathVariable Long categoryId,
                                  @Valid @RequestBody CategorySortRequest request){
        adminCategoryService.updateSort(shopId(),categoryId,request);
        return Result.ok();
    }

    @DeleteMapping("/{categoryId}")
    public Result<Void> delete(@PathVariable Long categoryId){
        adminCategoryService.delete(shopId(),categoryId);
        return Result.ok();
    }
}
