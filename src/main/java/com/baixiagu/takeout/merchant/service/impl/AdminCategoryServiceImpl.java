package com.baixiagu.takeout.merchant.service.impl;
import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;
import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.service.AdminCategoryService;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import com.baixiagu.takeout.shop.entity.ShopCategory;
import com.baixiagu.takeout.shop.mapper.ShopCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final ShopCategoryMapper shopCategoryMapper;

    @Override
    public List<AdminCategoryVO> list(Long shopId) {
        return shopCategoryMapper.selectByShop(shopId);
    }

    @Override
    public AdminCategoryVO create(Long shopId, CategorySaveRequest request) {
        String name = request.getName().trim();
        if(shopCategoryMapper.countByName(shopId,name,null)>0){
            throw new BizException(ErrorCode.PARAM_INVALID,"分类名称已存在");
        }

        ShopCategory category = new ShopCategory();
        category.setShopId(shopId);
        category.setName(name);
        category.setSort(request.getSort()!=null?request.getSort():shopCategoryMapper.selectMaxSort(shopId)+1);
        shopCategoryMapper.insert(category);
        return AdminCategoryVO.builder()
                .categoryId(String.valueOf(category.getId()))
                .name(category.getName())
                .sort(category.getSort())
                .productCount(0)
                .build();
    }
}
