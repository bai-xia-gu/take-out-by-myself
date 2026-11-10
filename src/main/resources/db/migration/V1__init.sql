-- =========================================================
-- 智速外卖 V1：建表
-- 约定：金额单位为「分」；状态用字符串枚举；图片存 URL
-- =========================================================

CREATE TABLE `user` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `openid`     VARCHAR(64)     NOT NULL COMMENT '微信 openid（mock 模式下由 code 派生）',
    `nickname`   VARCHAR(64)     NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar_url` VARCHAR(512)    NOT NULL DEFAULT '' COMMENT '头像',
    `phone`      VARCHAR(20)              DEFAULT NULL COMMENT '手机号',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_openid` (`openid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户';

CREATE TABLE `address` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `receiver_name` VARCHAR(32)     NOT NULL COMMENT '收货人',
    `phone`         VARCHAR(20)     NOT NULL,
    `detail`        VARCHAR(255)    NOT NULL COMMENT '详细地址',
    `is_default`    TINYINT         NOT NULL DEFAULT 0,
    `deleted`       TINYINT         NOT NULL DEFAULT 0,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user` (`user_id`, `deleted`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='收货地址';

CREATE TABLE `category` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '平台分类ID',
    `name`       VARCHAR(32)     NOT NULL,
    `icon_url`   VARCHAR(512)    NOT NULL DEFAULT '',
    `sort`       INT             NOT NULL DEFAULT 0,
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='平台分类（快餐/火锅…）';

CREATE TABLE `shop` (
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `category_id`      BIGINT UNSIGNED NOT NULL COMMENT '所属平台分类',
    `name`             VARCHAR(64)     NOT NULL,
    `cover_url`        VARCHAR(512)    NOT NULL DEFAULT '',
    `logo_url`         VARCHAR(512)    NOT NULL DEFAULT '',
    `rating`           DECIMAL(2, 1)   NOT NULL DEFAULT 5.0 COMMENT '评分 0.0-5.0',
    `month_sales`      INT             NOT NULL DEFAULT 0 COMMENT '月售',
    `min_order_amount` INT             NOT NULL DEFAULT 0 COMMENT '起送价（分）',
    `delivery_fee`     INT             NOT NULL DEFAULT 0 COMMENT '配送费（分）',
    `estimated_minutes` INT            NOT NULL DEFAULT 30 COMMENT '预计送达分钟',
    `business_status`  VARCHAR(16)     NOT NULL DEFAULT 'OPEN' COMMENT 'OPEN/CLOSED',
    `next_open_time`   VARCHAR(8)               DEFAULT NULL COMMENT '休息中下次营业时间 HH:mm',
    `notice`           VARCHAR(255)             DEFAULT NULL COMMENT '店铺公告',
    `address`          VARCHAR(255)             DEFAULT NULL COMMENT '店铺地址',
    `deleted`          TINYINT         NOT NULL DEFAULT 0,
    `created_at`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_category` (`category_id`, `deleted`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='店铺';

CREATE TABLE `shop_category` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '店内分类ID',
    `shop_id`    BIGINT UNSIGNED NOT NULL,
    `name`       VARCHAR(32)     NOT NULL COMMENT '如 热销/主食/饮品',
    `sort`       INT             NOT NULL DEFAULT 0,
    `deleted`    TINYINT         NOT NULL DEFAULT 0,
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_shop` (`shop_id`, `deleted`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='店内分类';

CREATE TABLE `product` (
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`     BIGINT UNSIGNED NOT NULL,
    `category_id` BIGINT UNSIGNED NOT NULL COMMENT '店内分类ID',
    `name`        VARCHAR(64)     NOT NULL,
    `image_url`   VARCHAR(512)    NOT NULL DEFAULT '',
    `description` VARCHAR(255)             DEFAULT NULL,
    `price`       INT             NOT NULL COMMENT '基础价（分）',
    `price_from`  TINYINT         NOT NULL DEFAULT 0 COMMENT '有规格组时为1，前端显示“起”',
    `month_sales` INT             NOT NULL DEFAULT 0,
    `status`      VARCHAR(16)     NOT NULL DEFAULT 'ON_SALE' COMMENT 'ON_SALE/OFF_SHELF',
    `sold_out`    TINYINT         NOT NULL DEFAULT 0,
    `sort`        INT             NOT NULL DEFAULT 0,
    `deleted`     TINYINT         NOT NULL DEFAULT 0,
    `created_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_shop_category` (`shop_id`, `category_id`, `deleted`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='商品';

CREATE TABLE `spec_group` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `name`       VARCHAR(32)     NOT NULL COMMENT '如 份量/辣度/加料',
    `type`       VARCHAR(16)     NOT NULL DEFAULT 'SINGLE' COMMENT 'SINGLE/MULTI',
    `required`   TINYINT         NOT NULL DEFAULT 1,
    `sort`       INT             NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `idx_product` (`product_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='规格组';

CREATE TABLE `spec_option` (
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `group_id`    BIGINT UNSIGNED NOT NULL,
    `name`        VARCHAR(32)     NOT NULL COMMENT '如 大份/微辣',
    `price_delta` INT             NOT NULL DEFAULT 0 COMMENT '加价（分）',
    `sort`        INT             NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `idx_group` (`group_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='规格选项';

CREATE TABLE `cart_item` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `product_id`    BIGINT UNSIGNED NOT NULL,
    `spec_key`      VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '选项ID升序拼接，用于同款判定',
    `spec_snapshot` JSON            NOT NULL COMMENT '下单时的规格快照',
    `unit_price`    INT             NOT NULL COMMENT '含规格加价的单价（分）',
    `quantity`      INT             NOT NULL,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_product_spec` (`user_id`, `product_id`, `spec_key`),
    KEY `idx_user_shop` (`user_id`, `shop_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='购物车（一个用户仅一个店铺）';

CREATE TABLE `orders` (
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_no`         VARCHAR(32)     NOT NULL COMMENT '对外订单号',
    `user_id`          BIGINT UNSIGNED NOT NULL,
    `shop_id`          BIGINT UNSIGNED NOT NULL,
    `status`           VARCHAR(24)     NOT NULL COMMENT 'PENDING_PAYMENT/PENDING_ACCEPT/PREPARING/DELIVERING/COMPLETED/CANCELLED',
    `items_total`      INT             NOT NULL DEFAULT 0 COMMENT '商品总额（分）',
    `delivery_fee`     INT             NOT NULL DEFAULT 0,
    `user_coupon_id`   BIGINT UNSIGNED          DEFAULT NULL,
    `coupon_name`      VARCHAR(64)              DEFAULT NULL,
    `discount_amount`  INT             NOT NULL DEFAULT 0 COMMENT '优惠金额（分）',
    `payable_amount`   INT             NOT NULL DEFAULT 0 COMMENT '应付/实付（分）',
    `refunded_amount`  INT             NOT NULL DEFAULT 0 COMMENT '已退款（分）',
    `receiver_name`    VARCHAR(32)     NOT NULL,
    `receiver_phone`   VARCHAR(20)     NOT NULL,
    `receiver_detail`  VARCHAR(255)    NOT NULL,
    `remark`           VARCHAR(255)             DEFAULT NULL,
    `request_id`       VARCHAR(64)     NOT NULL COMMENT '提交幂等号',
    `cancel_reason`    VARCHAR(255)             DEFAULT NULL,
    `created_at`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `paid_at`          DATETIME                 DEFAULT NULL,
    `expires_at`       DATETIME                 DEFAULT NULL COMMENT '待支付超时时间',
    `completed_at`     DATETIME                 DEFAULT NULL,
    `cancelled_at`     DATETIME                 DEFAULT NULL,
    `updated_at`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    UNIQUE KEY `uk_request` (`request_id`),
    KEY `idx_user_status` (`user_id`, `status`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='订单';

CREATE TABLE `order_item` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_id`      BIGINT UNSIGNED NOT NULL,
    `product_id`    BIGINT UNSIGNED NOT NULL,
    `name`          VARCHAR(64)     NOT NULL,
    `image_url`     VARCHAR(512)    NOT NULL DEFAULT '',
    `spec_text`     VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '如 大份,微辣',
    `spec_snapshot` JSON            NOT NULL,
    `unit_price`    INT             NOT NULL,
    `quantity`      INT             NOT NULL,
    `subtotal`      INT             NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_order` (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品';

CREATE TABLE `order_status_log` (
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_id`    BIGINT UNSIGNED NOT NULL,
    `status`      VARCHAR(24)     NOT NULL,
    `status_text` VARCHAR(32)     NOT NULL,
    `created_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_order` (`order_id`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='订单状态流转（详情页时间轴）';

CREATE TABLE `refund` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_id`      BIGINT UNSIGNED NOT NULL,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `amount`        INT             NOT NULL COMMENT '退款金额（分）',
    `reason`        VARCHAR(64)     NOT NULL,
    `description`   VARCHAR(500)             DEFAULT NULL,
    `images`        JSON                     DEFAULT NULL,
    `status`        VARCHAR(24)     NOT NULL DEFAULT 'PENDING_REVIEW' COMMENT 'PENDING_REVIEW/REFUNDED/REJECTED',
    `reject_reason` VARCHAR(255)             DEFAULT NULL,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `handled_at`    DATETIME                 DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_order` (`order_id`),
    KEY `idx_user` (`user_id`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='退款单';

CREATE TABLE `review` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_id`      BIGINT UNSIGNED NOT NULL,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `rating`        TINYINT         NOT NULL COMMENT '1-5 星',
    `content`       VARCHAR(500)             DEFAULT NULL,
    `images`        JSON                     DEFAULT NULL,
    `reply_content` VARCHAR(500)             DEFAULT NULL,
    `reply_time`    DATETIME                 DEFAULT NULL,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order` (`order_id`),
    KEY `idx_user` (`user_id`, `id`),
    KEY `idx_shop` (`shop_id`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='订单评价';

CREATE TABLE `coupon` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `name`          VARCHAR(64)     NOT NULL,
    `amount`        INT             NOT NULL COMMENT '面额（分）',
    `threshold`     INT             NOT NULL DEFAULT 0 COMMENT '门槛（分）',
    `total_count`   INT             NOT NULL DEFAULT 0,
    `claimed_count` INT             NOT NULL DEFAULT 0,
    `valid_days`    INT             NOT NULL DEFAULT 7 COMMENT '领取后有效天数',
    `status`        VARCHAR(16)     NOT NULL DEFAULT 'ACTIVE' COMMENT 'ACTIVE/DISABLED',
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_shop` (`shop_id`, `status`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券模板';

CREATE TABLE `user_coupon` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `coupon_id`     BIGINT UNSIGNED NOT NULL,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `status`        VARCHAR(16)     NOT NULL DEFAULT 'UNUSED' COMMENT 'UNUSED/USED/EXPIRED',
    `received_at`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `expire_at`     DATETIME        NOT NULL,
    `used_order_id` BIGINT UNSIGNED          DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_coupon` (`user_id`, `coupon_id`),
    KEY `idx_user_status` (`user_id`, `status`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户已领券';

CREATE TABLE `ai_session` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `scene`      VARCHAR(24)     NOT NULL COMMENT 'ORDER/CUSTOMER_SERVICE',
    `shop_id`    BIGINT UNSIGNED          DEFAULT NULL,
    `slots`      JSON                     DEFAULT NULL COMMENT '已抽取的槽位',
    `status`     VARCHAR(16)     NOT NULL DEFAULT 'ACTIVE',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `expire_at`  DATETIME        NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_user_scene` (`user_id`, `scene`, `status`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='AI 会话';

CREATE TABLE `ai_message` (
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `session_id` BIGINT UNSIGNED NOT NULL,
    `role`       VARCHAR(16)     NOT NULL COMMENT 'user/assistant',
    `content`    TEXT            NOT NULL,
    `type`       VARCHAR(24)              DEFAULT NULL COMMENT 'text/cart_card/tool',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_session` (`session_id`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='AI 消息';

CREATE TABLE `ticket` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT UNSIGNED NOT NULL,
    `shop_id`       BIGINT UNSIGNED          DEFAULT NULL,
    `order_id`      BIGINT UNSIGNED          DEFAULT NULL,
    `description`   VARCHAR(500)    NOT NULL,
    `images`        JSON                     DEFAULT NULL,
    `status`        VARCHAR(16)     NOT NULL DEFAULT 'PENDING' COMMENT 'PENDING/REPLIED',
    `reply_content` VARCHAR(500)             DEFAULT NULL,
    `reply_time`    DATETIME                 DEFAULT NULL,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user` (`user_id`, `id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='转人工工单';

CREATE TABLE `search_hotword` (
    `id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `word`    VARCHAR(32)     NOT NULL,
    `sort`    INT             NOT NULL DEFAULT 0,
    `enabled` TINYINT         NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='搜索热词';
