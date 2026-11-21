package com.baixiagu.takeout.merchant.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCategoryVO {

    private String categoryId;
    private String name;
    private int sort;
    /** 分类下未删除的商品数，前端用来判断能不能删 */
    private long productCount;
}
