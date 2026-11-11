package com.baixiagu.takeout.common;

import lombok.Data;

@Data
public class PageQuery {

    private int page = 1;
    private int size = 10;

    /** 只做上限保护；page 的越界纠偏交给 PageHelper 的 reasonable */
    public PageQuery normalize() {
        this.page = Math.max(this.page, 1);
        this.size = Math.min(Math.max(this.size, 1), 50);
        return this;
    }
}