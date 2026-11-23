package com.baixiagu.takeout.merchant.service;

import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.dto.CategorySortRequest;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import jakarta.validation.Valid;

import java.util.List;


public interface AdminCategoryService {

    List<AdminCategoryVO> list(Long shopId);

    AdminCategoryVO create(Long aLong, @Valid CategorySaveRequest request);

    void rename(Long shopId, Long categoryId, CategorySaveRequest request);

    void updateSort(Long shopId, Long categoryId, CategorySortRequest request);

    void delete(Long aLong, Long categoryId);
}
