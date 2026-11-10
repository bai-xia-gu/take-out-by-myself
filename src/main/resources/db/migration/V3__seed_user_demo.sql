-- =========================================================
-- 智速外卖 V3：本地演示用的「用户侧」种子数据
-- 说明：mock 登录的 openid 固定为 mock_openid_001，
--       因此固定 id=1 的用户就是小程序里登录后看到的那个账号。
-- =========================================================

INSERT IGNORE INTO `user` (id, openid, nickname, avatar_url, phone)
VALUES (1, 'mock_openid_001', '吃货一号',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=cute%20cartoon%20foodie%20avatar%2C%20flat%20illustration&image_size=square',
        NULL);

INSERT IGNORE INTO address (id, user_id, receiver_name, phone, detail, is_default, deleted)
VALUES (1, 1, '小智', '13800000001', '幸福路 18 号 3 单元 502 室', 1, 0),
       (2, 1, '小智', '13800000001', '银河广场 A 座 12 层 1203', 0, 0);

-- ---------- 已领优惠券 ----------
INSERT IGNORE INTO user_coupon (id, coupon_id, user_id, shop_id, status, received_at, expire_at, used_order_id)
VALUES (1, 1, 1, 1, 'USED', DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 1),
       (2, 2, 1, 2, 'UNUSED', DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), NULL),
       (3, 3, 1, 3, 'UNUSED', DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), NULL);

-- ---------- 订单 1：已完成（可用于评价/查看时间轴） ----------
INSERT IGNORE INTO orders (id, order_no, user_id, shop_id, status, items_total, delivery_fee, user_coupon_id, coupon_name,
                    discount_amount, payable_amount, refunded_amount, receiver_name, receiver_phone, receiver_detail,
                    remark, request_id, cancel_reason, created_at, paid_at, expires_at, completed_at)
VALUES (1, 'ZS20261109000001', 1, 1, 'COMPLETED', 4080, 300, 1, '香锅满 30 减 5', 500, 3880, 0,
        '小智', '13800000001', '幸福路 18 号 3 单元 502 室', '少辣，多加点宽粉', 'seed-request-1', NULL,
        DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY),
        DATE_SUB(NOW(), INTERVAL 40 MINUTE));

INSERT IGNORE INTO order_item (id, order_id, product_id, name, image_url, spec_text, spec_snapshot, unit_price, quantity, subtotal)
VALUES (1, 1, 1001, '麻辣香锅（单人份）',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20mala%20xiang%20guo%20stir%20fry%20in%20black%20pot%2C%20chinese%20food%20photography&image_size=square',
        '微辣,加宽粉',
        '[{"groupId":"2001","groupName":"辣度","optionId":"3001","optionName":"微辣","priceDelta":0},{"groupId":"2002","groupName":"加料","optionId":"3005","optionName":"加宽粉","priceDelta":300}]',
        3180, 1, 3180),
       (2, 1, 1004, '酸梅汤',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20sour%20plum%20drink%20in%20glass%20with%20ice%2C%20food%20photography&image_size=square',
        '', '[]', 600, 1, 600);

INSERT IGNORE INTO order_status_log (order_id, status, status_text, created_at)
VALUES (1, 'PENDING_PAYMENT', '待支付', DATE_SUB(NOW(), INTERVAL 1 DAY)),
       (1, 'PENDING_ACCEPT', '商家已接单', DATE_SUB(NOW(), INTERVAL 1 DAY)),
       (1, 'PREPARING', '商家制作中', DATE_SUB(NOW(), INTERVAL 1 DAY)),
       (1, 'DELIVERING', '骑手配送中', DATE_SUB(NOW(), INTERVAL 1 DAY)),
       (1, 'COMPLETED', '订单已完成', DATE_SUB(NOW(), INTERVAL 40 MINUTE));

-- ---------- 订单 2：待支付（可测倒计时/收银台/取消） ----------
INSERT IGNORE INTO orders (id, order_no, user_id, shop_id, status, items_total, delivery_fee, user_coupon_id, coupon_name,
                    discount_amount, payable_amount, refunded_amount, receiver_name, receiver_phone, receiver_detail,
                    remark, request_id, cancel_reason, created_at, paid_at, expires_at, completed_at)
VALUES (2, 'ZS20261110000002', 1, 2, 'PENDING_PAYMENT', 2600, 200, NULL, NULL, 0, 2800, 0,
        '小智', '13800000001', '银河广场 A 座 12 层 1203', NULL, 'seed-request-2', NULL,
        NOW(), NULL, DATE_ADD(NOW(), INTERVAL 15 MINUTE), NULL);

INSERT IGNORE INTO order_item (id, order_id, product_id, name, image_url, spec_text, spec_snapshot, unit_price, quantity, subtotal)
VALUES (3, 2, 1005, '红烧牛肉面',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20braised%20beef%20noodle%20soup%20in%20ceramic%20bowl%2C%20steam%2C%20food%20photography&image_size=square',
        '大碗,宽面',
        '[{"groupId":"2003","groupName":"份量","optionId":"3007","optionName":"大碗","priceDelta":400},{"groupId":"2004","groupName":"面型","optionId":"3009","optionName":"宽面","priceDelta":0}]',
        3000, 1, 3000);

INSERT IGNORE INTO order_status_log (order_id, status, status_text, created_at)
VALUES (2, 'PENDING_PAYMENT', '待支付', NOW());

-- ---------- 订单 3：已接单 + 退款审核中（可测售后页） ----------
INSERT IGNORE INTO orders (id, order_no, user_id, shop_id, status, items_total, delivery_fee, user_coupon_id, coupon_name,
                    discount_amount, payable_amount, refunded_amount, receiver_name, receiver_phone, receiver_detail,
                    remark, request_id, cancel_reason, created_at, paid_at, expires_at, completed_at)
VALUES (3, 'ZS20261110000003', 1, 3, 'PENDING_ACCEPT', 2700, 200, 3, '便当满 20 减 3', 300, 2600, 0,
        '小智', '13800000001', '幸福路 18 号 3 单元 502 室', '不要葱', 'seed-request-3', NULL,
        DATE_SUB(NOW(), INTERVAL 25 MINUTE), DATE_SUB(NOW(), INTERVAL 24 MINUTE), DATE_SUB(NOW(), INTERVAL 10 MINUTE), NULL);

INSERT IGNORE INTO order_item (id, order_id, product_id, name, image_url, spec_text, spec_snapshot, unit_price, quantity, subtotal)
VALUES (4, 3, 1008, '台式卤肉饭',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=taiwanese%20braised%20pork%20rice%20bowl%20with%20egg%2C%20food%20photography&image_size=square',
        '', '[]', 2200, 1, 2200),
       (5, 3, 1010, '紫菜蛋花汤',
        'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=seaweed%20egg%20drop%20soup%20in%20bowl%2C%20food%20photography&image_size=square',
        '', '[]', 500, 1, 500);

INSERT IGNORE INTO order_status_log (order_id, status, status_text, created_at)
VALUES (3, 'PENDING_PAYMENT', '待支付', DATE_SUB(NOW(), INTERVAL 25 MINUTE)),
       (3, 'PENDING_ACCEPT', '商家已接单', DATE_SUB(NOW(), INTERVAL 24 MINUTE));

-- ---------- 评价 1 条 ----------
INSERT IGNORE INTO review (id, order_id, user_id, shop_id, rating, content, images, reply_content, reply_time, created_at)
VALUES (1, 1, 1, 1, 5, '香锅很香，微辣刚好，配送也快，下次还点。',
        '["https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20mala%20xiang%20guo%20top%20view%2C%20food%20photography&image_size=square"]',
        '感谢支持，欢迎下次光临～', DATE_SUB(NOW(), INTERVAL 30 MINUTE), DATE_SUB(NOW(), INTERVAL 35 MINUTE));

-- ---------- 退款 1 条（待商家审核） ----------
INSERT IGNORE INTO refund (id, order_id, user_id, shop_id, amount, reason, description, images, status, reject_reason, created_at, handled_at)
VALUES (1, 3, 1, 3, 500, '少送商品', '紫菜蛋花汤没有送到', NULL, 'PENDING_REVIEW', NULL,
        DATE_SUB(NOW(), INTERVAL 10 MINUTE), NULL);
