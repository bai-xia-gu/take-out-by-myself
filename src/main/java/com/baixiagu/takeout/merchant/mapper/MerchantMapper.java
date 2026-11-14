package com.baixiagu.takeout.merchant.mapper;

import com.baixiagu.takeout.merchant.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * merchant 表
 */
@Mapper
public interface MerchantMapper {

    Merchant selectByAccount(@Param("account") String account);

    Merchant selectById(@Param("id") Long id);
}
