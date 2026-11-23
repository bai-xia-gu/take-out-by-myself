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


    /**
     * 校验分类是否存在
     */
    ShopCategory selectByIdAndShop(@Param("shopId") Long shopId,
                              @Param("categoryId") Long categoryId);
    /**
     * 更新分类名称
     */
    int updateName(@Param("shopId") Long shopId,
                    @Param("categoryId") Long categoryId,
                    @Param("name") String name);


    /**
     * 查询店铺下的所有分类id
     */
    List<Long> selectIdsByShop(@Param("shopId") Long shopId);

    /**
     * 更新分类排序
     */
    int updateSort(@Param("shopId") Long shopId,
                    @Param("categoryId") Long categoryId,
                    @Param("sort") int sort);

    /**
     * 查询分类下的商品数量
     */
    int countProducts(Long shopId, Long categoryId);

    /**
     * 软删除分类
     */
    void softDelete(Long shopId, Long categoryId);
}
