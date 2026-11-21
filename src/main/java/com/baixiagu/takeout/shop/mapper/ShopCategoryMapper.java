package com.baixiagu.takeout.shop.mapper;

import com.baixiagu.takeout.merchant.vo.AdminCategoryVO;
import com.baixiagu.takeout.shop.entity.ShopCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * shop_category 关联表
 */
@Mapper
public interface ShopCategoryMapper {

    List<AdminCategoryVO> selectByShop(@Param("shopId") Long shopId);

    /**
     * 查询店铺下的分类名称是否存在
     */
    int countByName(@Param("shopId") Long shopId, @Param("name") String name, @Param("excludeId") Long excludeId);

    /**
     * 查询店铺下的最大排序值
     */
    int selectMaxSort(@Param("shopId") Long shopId);

    /**
     * 插入分类
     */
    void insert(ShopCategory category);


}
