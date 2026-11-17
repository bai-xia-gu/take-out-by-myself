package com.baixiagu.takeout.shop.mapper;

import com.baixiagu.takeout.shop.vo.ShopVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * shop 表
 */
@Mapper
public interface ShopMapper {
    // 店长登录查询店铺信息
    ShopVO selectAdminShop(@Param("shopId") Long shopId);
}
