package com.baixiagu.takeout.merchant.service.impl;

import com.baixiagu.takeout.auth.JwtProperties;
import com.baixiagu.takeout.auth.JwtService;
import com.baixiagu.takeout.auth.PasswordUtil;
import com.baixiagu.takeout.common.BizException;
import com.baixiagu.takeout.common.ErrorCode;
import com.baixiagu.takeout.merchant.dto.AdminLoginRequest;
import com.baixiagu.takeout.merchant.entity.Merchant;
import com.baixiagu.takeout.merchant.mapper.MerchantMapper;
import com.baixiagu.takeout.merchant.service.AdminAuthService;
import com.baixiagu.takeout.merchant.vo.AdminLoginVO;
import com.baixiagu.takeout.merchant.vo.AdminProfileVO;
import com.baixiagu.takeout.shop.mapper.ShopMapper;
import com.baixiagu.takeout.shop.vo.ShopVO;
import org.springframework.stereotype.Service;

/**
 * 商家端认证业务实现
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {


    private final MerchantMapper merchantMapper;
    private final ShopMapper shopMapper;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    public AdminAuthServiceImpl(MerchantMapper merchantMapper, ShopMapper shopMapper, JwtService jwtService, JwtProperties jwtProperties) {
        this.merchantMapper = merchantMapper;
        this.shopMapper = shopMapper;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public AdminLoginVO login(AdminLoginRequest request) {
        String account = request.getAccount().trim(); // 账号去空格
        Merchant merchant = merchantMapper.selectByAccount(account);
        //
        if(merchant == null || !PasswordUtil.matches(request.getPassword(),merchant.getSalt(), merchant.getPasswordHash())){
            throw new BizException(ErrorCode.UNAUTHORIZED,"账号或密码错误");
        }

        ShopVO shop = shopMapper.selectAdminShop(merchant.getShopId());
        String token = jwtService.issueMerchant(merchant.getId(),merchant.getShopId());

        return AdminLoginVO.builder()
                .token(token)
                .expiresIn(jwtProperties.getExpireMillis())
                .merchantId(String.valueOf(merchant.getId()))
                .shopId(String.valueOf(merchant.getShopId()))
                .shopName(shop==null?"":shop.getName())
                .build();
    }

    @Override
    public AdminProfileVO profile(Long merchantId) {
        Merchant merchant = merchantMapper.selectById(merchantId);
        if(merchant == null){
            throw new BizException(ErrorCode.UNAUTHORIZED);
        }

        ShopVO shop = shopMapper.selectAdminShop(merchant.getShopId());
        if(shop == null){
            throw new BizException(ErrorCode.SHOP_NOT_FOUND);
        }

        return AdminProfileVO.builder()
                .merchantId(String.valueOf(merchant.getId()))
                .account(merchant.getAccount())
                .shop(shop)
                .build();
    }

}
