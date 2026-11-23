package com.baixiagu.takeout.merchant.service.impl;
import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;
import com.baixiagu.takeout.merchant.dto.CategorySaveRequest;
import com.baixiagu.takeout.merchant.dto.CategorySortRequest;
import com.baixiagu.takeout.merchant.service.AdminCategoryService;
import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import com.baixiagu.takeout.shop.entity.ShopCategory;
import com.baixiagu.takeout.shop.mapper.ShopCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final ShopCategoryMapper shopCategoryMapper;

    /**
     * 查询店铺下的所有分类
     */
    @Override
    public List<AdminCategoryVO> list(Long shopId) {
        return shopCategoryMapper.selectByShop(shopId);
    }

    /**
     * 创建分类
     */
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

    /**
     * 重命名分类
     */
    @Override
    public void rename(Long shopId, Long categoryId, CategorySaveRequest request) {
        requireCategory(shopId,categoryId);
        String name = request.getName().trim();
        if(shopCategoryMapper.countByName(shopId,name,categoryId)>0){
            throw new BizException(ErrorCode.PARAM_INVALID,"分类名称已存在");
        }
        shopCategoryMapper.updateName(shopId,categoryId,name);
    }



    /**
     * 校验分类是否存在
     */
    private void requireCategory(Long shopId, Long categoryId){
        if(shopCategoryMapper.selectByIdAndShop(shopId,categoryId)==null){
            throw new BizException(ErrorCode.NOT_FOUND);
        }
    }

    @Override
    public void updateSort(Long shopId, Long categoryId, CategorySortRequest request) {
        List<Long> ids = new ArrayList<>(shopCategoryMapper.selectIdsByShop(shopId));
        if(!ids.remove(categoryId)){
            throw new BizException(ErrorCode.NOT_FOUND);
        }

        int index = Math.min(Math.max(request.getSort()-1,0),ids.size()); // 为什么减一 因为sort是从1开始的
        ids.add(index,categoryId);
        for(int i=0;i<ids.size();i++){
            shopCategoryMapper.updateSort(shopId,ids.get(i),i+1);
        }
    }

    @Override
    public void delete(Long shopId, Long categoryId) {
        requireCategory(shopId,categoryId);
        if(shopCategoryMapper.countProducts(shopId,categoryId)>0){
            throw new BizException(ErrorCode.CATEGORY_NOT_EMPTY,"分类下有商品",Map.of("hint","该分类下仍有商品，请先移出。"));
        }
        shopCategoryMapper.softDelete(shopId,categoryId);
    }

}
