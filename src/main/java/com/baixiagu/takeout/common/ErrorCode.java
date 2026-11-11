package com.baixiagu.takeout.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 业务错误码（接口文档 1.4）
 * 规则：4xxyy，4xx 为类别，yy 为序号；新增码只追加不改义。
 * 注意：只有 401/403 用 HTTP 语义，其余失败仍返回 HTTP 200 + 业务码。
 */
@Getter
public enum ErrorCode {
    // 枚举值

    SUCCESS(0, "success", HttpStatus.OK),

    // 400xx 参数
    PARAM_INVALID(40001, "参数不合法", HttpStatus.OK),
    PARAM_MISSING(40002, "缺少必填参数", HttpStatus.OK),
    REMARK_TOO_LONG(40003, "备注超长", HttpStatus.OK),
    IMAGE_LIMIT_EXCEEDED(40004, "图片数量超限", HttpStatus.OK),
    FILE_TYPE_NOT_ALLOWED(40005, "文件类型不支持", HttpStatus.OK),
    FILE_TOO_LARGE(40006, "文件过大", HttpStatus.OK),

    // 401xx 认证
    UNAUTHORIZED(40101, "未登录或令牌无效", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(40102, "登录已过期", HttpStatus.UNAUTHORIZED),
    ROLE_MISMATCH(40103, "角色不匹配", HttpStatus.FORBIDDEN),
    INTERNAL_TOKEN_INVALID(40104, "内部令牌无效", HttpStatus.UNAUTHORIZED),

    // 403xx 权限
    FORBIDDEN(40301, "无权访问该资源", HttpStatus.FORBIDDEN),

    // 404xx 不存在
    NOT_FOUND(40401, "资源不存在", HttpStatus.OK),
    SHOP_NOT_FOUND(40402, "店铺不存在", HttpStatus.OK),
    ORDER_NOT_FOUND(40403, "订单不存在", HttpStatus.OK),
    PRODUCT_NOT_FOUND(40404, "商品不存在", HttpStatus.OK),

    // 409xx 冲突
    STATE_CONFLICT(40901, "状态已变更，请刷新", HttpStatus.OK),
    CART_SHOP_CONFLICT(40902, "购物车已有其他店铺商品", HttpStatus.OK),
    REVIEW_ALREADY_EXISTS(40903, "该订单已评价", HttpStatus.OK),
    COUPON_ALREADY_CLAIMED(40904, "该券已领取", HttpStatus.OK),
    COUPON_ALREADY_USED(40905, "该券已使用", HttpStatus.OK),

    // 422xx 业务规则
    SHOP_CLOSED(42201, "店铺休息中", HttpStatus.OK),
    BELOW_MIN_ORDER(42202, "未达起送价", HttpStatus.OK),
    PRODUCT_OFF_SHELF(42203, "商品已下架", HttpStatus.OK),
    PRODUCT_SOLD_OUT(42204, "商品已售罄", HttpStatus.OK),
    COUPON_UNAVAILABLE(42205, "优惠券不可用", HttpStatus.OK),
    ADDRESS_INVALID(42206, "收货地址无效", HttpStatus.OK),
    ORDER_NOT_REVIEWABLE(42207, "订单当前不可评价", HttpStatus.OK),
    ORDER_NOT_REFUNDABLE(42208, "订单当前不可退款", HttpStatus.OK),
    SPEC_REQUIRED(42209, "必选规格未选择", HttpStatus.OK),
    COUPON_SOLD_OUT(42210, "该券已抢完", HttpStatus.OK),
    COUPON_EXPIRED(42211, "优惠券已过期", HttpStatus.OK),
    QUOTE_STALE(42212, "结算信息已变化", HttpStatus.OK),
    CATEGORY_NOT_EMPTY(42213, "分类下仍有商品", HttpStatus.OK),
    PRODUCT_HAS_ORDERS(42214, "商品存在历史订单，只能下架", HttpStatus.OK),
    STATS_NO_DATA(42215, "当前区间无数据", HttpStatus.OK),

    // 429xx 限流
    COUPON_CLAIM_TOO_FREQUENT(42901, "操作过于频繁", HttpStatus.OK),

    // 5xxxx 服务端
    INTERNAL_ERROR(50001, "服务器开小差了", HttpStatus.OK),
    AI_UNAVAILABLE(50201, "AI 服务暂不可用", HttpStatus.OK),
    AI_TIMEOUT(50202, "AI 响应超时", HttpStatus.OK);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
