-- V4：店长账号（接口文档 2.4「账号由种子数据预置」）
-- 注意：这是新增表，V1 的 20 张业务表里没有它
CREATE TABLE `merchant` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '店长ID',
    `account`       VARCHAR(32)     NOT NULL COMMENT '登录账号',
    `password_hash` CHAR(64)        NOT NULL COMMENT 'SHA-256(salt + password) 的十六进制',
    `salt`          VARCHAR(16)     NOT NULL COMMENT '每个账号独立盐',
    `name`          VARCHAR(32)     NOT NULL COMMENT '店长姓名',
    `shop_id`       BIGINT UNSIGNED NOT NULL COMMENT '所属店铺',
    `role`          VARCHAR(16)     NOT NULL DEFAULT 'MERCHANT',
    `deleted`       TINYINT         NOT NULL DEFAULT 0,
    `created_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_account` (`account`),
    KEY `idx_shop` (`shop_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='店长账号';

-- 登录用例：admin / 123456，绑定 1 号店（V2 种子里的「川味小馆·麻辣香锅」）
INSERT INTO `merchant` (`id`, `account`, `password_hash`, `salt`, `name`, `shop_id`, `role`)
VALUES (1001, 'admin', SHA2(CONCAT('z8f3k2', '123456'), 256), 'z8f3k2', '王老板', 1, 'MERCHANT');
