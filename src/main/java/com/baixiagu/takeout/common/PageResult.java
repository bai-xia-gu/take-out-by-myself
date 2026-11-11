package com.baixiagu.takeout.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private int page;
    private int size;
    private long total;
    private List<T> list;

    /** 从 PageHelper 的 PageInfo 转换，保证对外结构恒为 {page,size,total,list} */
    public static <T> PageResult<T> of(PageQuery query, PageInfo<T> pageInfo) {
        PageResult<T> result = new PageResult<>();
        result.page = query.getPage();
        result.size = query.getSize();
        result.total = pageInfo.getTotal();
        result.list = pageInfo.getList();
        return result;
    }
}