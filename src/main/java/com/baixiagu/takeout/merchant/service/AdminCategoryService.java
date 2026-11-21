package com.baixiagu.takeout.merchant.service;

import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import jakarta.validation.Valid;

import java.util.List;


public interface AdminCategoryService {

    List<AdminCategoryVO> list(Long shopId);

    AdminCategoryVO create(Long aLong, @Valid CategorySaveRequest request);
}
