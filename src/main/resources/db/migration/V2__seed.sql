-- =========================================================
-- 智速外卖 V2：演示数据
-- =========================================================

INSERT INTO category (id, name, icon_url, sort) VALUES
    (1, '快餐便当', '', 1),
    (2, '面食粉粥', '', 2),
    (3, '汉堡炸鸡', '', 3),
    (4, '火锅烧烤', '', 4),
    (5, '甜品饮品', '', 5),
    (6, '轻食沙拉', '', 6),
    (7, '早餐', '', 7);

-- ---------- 店铺 ----------
INSERT INTO shop (id, category_id, name, cover_url, logo_url, rating, month_sales, min_order_amount, delivery_fee,
                  estimated_minutes, business_status, next_open_time, notice, address)
VALUES
    (1, 4, '川味小馆·麻辣香锅',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20sichuan%20mala%20hotpot%20with%20fresh%20vegetables%20and%20beef%2C%20restaurant%20table%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=sichuan%20spicy%20food%20logo%20icon%2C%20flat%20design&image_size=square',
     4.8, 3200, 2000, 300, 35, 'OPEN', NULL, '本店麻辣香锅现炒现做，微辣可备注', '幸福路 18 号'),
    (2, 2, '老街牛肉面',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20beef%20noodle%20soup%20restaurant%20counter%2C%20warm%20light%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=noodle%20bowl%20logo%20icon%2C%20flat%20design&image_size=square',
     4.7, 5600, 1500, 200, 25, 'OPEN', NULL, '老汤现熬，每日现切牛肉', '建设街 6 号'),
    (3, 1, '元气便当',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=japanese%20style%20bento%20rice%20box%20with%20braised%20pork%20and%20vegetables%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=bento%20box%20logo%20icon%2C%20flat%20design&image_size=square',
     4.6, 4100, 1800, 200, 30, 'OPEN', NULL, '每日新鲜现做，米饭可免费加', '青年路 22 号'),
    (4, 3, '脆皮炸鸡研究所',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=crispy%20fried%20chicken%20bucket%20with%20french%20fries%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fried%20chicken%20logo%20icon%2C%20flat%20design&image_size=square',
     4.5, 2800, 2500, 400, 30, 'OPEN', NULL, '现炸出锅，超时免单', '美食街 9 号'),
    (5, 6, '轻享沙拉',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20salad%20bowl%20with%20avocado%20chicken%20and%20greens%2C%20bright%20light%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=salad%20bowl%20logo%20icon%2C%20flat%20design&image_size=square',
     4.9, 900, 3000, 500, 30, 'OPEN', NULL, '低卡轻食，酱料另装', '银河广场 3 层'),
    (6, 4, '深夜烧烤摊',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20bbq%20skewers%20on%20charcoal%20grill%20at%20night%20market%2C%20food%20photography&image_size=landscape_4_3',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=bbq%20skewer%20logo%20icon%2C%20flat%20design&image_size=square',
     4.4, 1200, 2000, 300, 40, 'CLOSED', '18:00', '晚上 6 点开烤，凌晨 2 点收摊', '夜市东街 1 号');

-- ---------- 店内分类 ----------
INSERT INTO shop_category (id, shop_id, name, sort) VALUES
    (11, 1, '招牌香锅', 1), (12, 1, '小吃', 2), (13, 1, '饮品', 3),
    (21, 2, '面食', 1), (22, 2, '小菜', 2),
    (31, 3, '招牌便当', 1), (32, 3, '汤品', 2),
    (41, 4, '炸鸡', 1), (42, 4, '小食', 2),
    (51, 5, '沙拉', 1), (52, 5, '饮品', 2),
    (61, 6, '烤串', 1);

-- ---------- 商品 ----------
INSERT INTO product (id, shop_id, category_id, name, image_url, description, price, price_from, month_sales, status, sold_out, sort)
VALUES
    (1001, 1, 11, '麻辣香锅（单人份）',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20mala%20xiang%20guo%20stir%20fry%20in%20black%20pot%2C%20chinese%20food%20photography&image_size=square',
     '荤素搭配 8 种食材，现炒出锅', 2880, 1, 1200, 'ON_SALE', 0, 1),
    (1002, 1, 12, '香辣鸡翅',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20chicken%20wings%20close%20up%2C%20food%20photography&image_size=square',
     '外酥里嫩，微辣', 1200, 0, 860, 'ON_SALE', 0, 2),
    (1003, 1, 12, '红糖冰粉',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20bingfen%20grass%20jelly%20dessert%20bowl%20with%20peanuts%2C%20food%20photography&image_size=square',
     '解辣神器', 800, 0, 640, 'ON_SALE', 0, 3),
    (1004, 1, 13, '酸梅汤',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20sour%20plum%20drink%20in%20glass%20with%20ice%2C%20food%20photography&image_size=square',
     '冰镇自熬', 600, 0, 520, 'ON_SALE', 0, 4),

    (1005, 2, 21, '红烧牛肉面',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chinese%20braised%20beef%20noodle%20soup%20in%20ceramic%20bowl%2C%20steam%2C%20food%20photography&image_size=square',
     '老汤慢炖 4 小时，牛肉大块', 2600, 1, 2100, 'ON_SALE', 0, 1),
    (1006, 2, 21, '番茄鸡蛋面',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tomato%20egg%20noodle%20soup%20chinese%20style%2C%20food%20photography&image_size=square',
     '酸甜开胃', 1800, 0, 900, 'ON_SALE', 0, 2),
    (1007, 2, 22, '凉拌黄瓜',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=smashed%20cucumber%20salad%20with%20garlic%20chinese%20style%2C%20food%20photography&image_size=square',
     '爽脆解腻', 800, 0, 430, 'ON_SALE', 0, 3),

    (1008, 3, 31, '台式卤肉饭',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=taiwanese%20braised%20pork%20rice%20bowl%20with%20egg%2C%20food%20photography&image_size=square',
     '肥瘦相间，配卤蛋', 2200, 0, 1800, 'ON_SALE', 0, 1),
    (1009, 3, 31, '照烧鸡腿饭',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=teriyaki%20chicken%20rice%20bowl%2C%20food%20photography&image_size=square',
     '整只去骨鸡腿', 2400, 0, 1500, 'ON_SALE', 0, 2),
    (1010, 3, 32, '紫菜蛋花汤',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=seaweed%20egg%20drop%20soup%20in%20bowl%2C%20food%20photography&image_size=square',
     '清淡暖胃', 500, 0, 990, 'ON_SALE', 0, 3),

    (1011, 4, 41, '脆皮炸鸡（2 块）',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=crispy%20fried%20chicken%20pieces%20close%20up%2C%20golden%20crust%2C%20food%20photography&image_size=square',
     '现点现炸，外脆里嫩', 1500, 0, 1300, 'ON_SALE', 0, 1),
    (1012, 4, 41, '香辣鸡腿堡',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=spicy%20chicken%20burger%2C%20food%20photography&image_size=square',
     '整块鸡腿排', 1800, 0, 1100, 'ON_SALE', 0, 2),
    (1013, 4, 42, '黄金薯条',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=golden%20french%20fries%20in%20paper%20box%2C%20food%20photography&image_size=square',
     '现炸酥脆', 900, 1, 800, 'ON_SALE', 0, 3),

    (1014, 5, 51, '鸡胸牛油果沙拉',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chicken%20avocado%20salad%20bowl%2C%20healthy%20food%20photography&image_size=square',
     '高蛋白低脂', 3200, 0, 320, 'ON_SALE', 0, 1),
    (1015, 5, 51, '藜麦虾仁沙拉',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=quinoa%20shrimp%20salad%20bowl%2C%20healthy%20food%20photography&image_size=square',
     '鲜虾配藜麦', 3500, 0, 210, 'ON_SALE', 0, 2),

    (1016, 6, 61, '羊肉串（5 串）',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=lamb%20skewers%20grilled%20on%20charcoal%2C%20chinese%20bbq%2C%20food%20photography&image_size=square',
     '现串现烤', 2500, 0, 700, 'ON_SALE', 0, 1),
    (1017, 6, 61, '蒜蓉烤茄子',
     'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=grilled%20eggplant%20with%20garlic%20chinese%20bbq%2C%20food%20photography&image_size=square',
     '蒜香浓郁', 1800, 0, 380, 'ON_SALE', 0, 2);

-- ---------- 规格：麻辣香锅 ----------
INSERT INTO spec_group (id, product_id, name, type, required, sort) VALUES
    (2001, 1001, '辣度', 'SINGLE', 1, 1),
    (2002, 1001, '加料', 'MULTI', 0, 2);
INSERT INTO spec_option (id, group_id, name, price_delta, sort) VALUES
    (3001, 2001, '微辣', 0, 1),
    (3002, 2001, '中辣', 0, 2),
    (3003, 2001, '特辣', 0, 3),
    (3004, 2002, '加土豆片', 200, 1),
    (3005, 2002, '加宽粉', 300, 2);

-- ---------- 规格：红烧牛肉面 ----------
INSERT INTO spec_group (id, product_id, name, type, required, sort) VALUES
    (2003, 1005, '份量', 'SINGLE', 1, 1),
    (2004, 1005, '面型', 'SINGLE', 1, 2);
INSERT INTO spec_option (id, group_id, name, price_delta, sort) VALUES
    (3006, 2003, '小碗', 0, 1),
    (3007, 2003, '大碗', 400, 2),
    (3008, 2004, '细面', 0, 1),
    (3009, 2004, '宽面', 0, 2);

-- ---------- 规格：黄金薯条 ----------
INSERT INTO spec_group (id, product_id, name, type, required, sort) VALUES
    (2005, 1013, '份量', 'SINGLE', 1, 1);
INSERT INTO spec_option (id, group_id, name, price_delta, sort) VALUES
    (3010, 2005, '中份', 0, 1),
    (3011, 2005, '大份', 300, 2);

-- ---------- 优惠券 ----------
INSERT INTO coupon (id, shop_id, name, amount, threshold, total_count, claimed_count, valid_days, status) VALUES
    (1, 1, '香锅满 30 减 5', 500, 3000, 1000, 0, 7, 'ACTIVE'),
    (2, 2, '牛肉面满 25 减 4', 400, 2500, 1000, 0, 7, 'ACTIVE'),
    (3, 3, '便当满 20 减 3', 300, 2000, 1000, 0, 7, 'ACTIVE'),
    (4, 5, '沙拉满 40 减 8', 800, 4000, 500, 0, 7, 'ACTIVE');

-- ---------- 搜索热词 ----------
INSERT INTO search_hotword (word, sort, enabled) VALUES
    ('麻辣香锅', 1, 1),
    ('牛肉面', 2, 1),
    ('炸鸡', 3, 1),
    ('卤肉饭', 4, 1),
    ('沙拉', 5, 1),
    ('烤串', 6, 1),
    ('奶茶', 7, 1),
    ('早餐', 8, 1);
