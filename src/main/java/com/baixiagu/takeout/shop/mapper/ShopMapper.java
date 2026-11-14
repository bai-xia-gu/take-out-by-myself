package com.baixiagu.takeout.shop.mapper;

import com.baixiagu.takeout.shop.vo.ShopVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * shop 表
 */
@Mapper
public interface ShopMapper {

    ShopVO selectAdminShop(@Param("shopId") Long shopId);
}
