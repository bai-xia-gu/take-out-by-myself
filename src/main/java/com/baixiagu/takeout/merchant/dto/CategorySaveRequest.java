package com.baixiagu.takeout.merchant.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategorySaveRequest {

    @NotBlank(message = "分类名称不能为空")
    @Size(max = 20,message = "分类名称最多20个字符")
    private String name;


    @Min(value = 1,message = "排序不能小于1")
    private Integer sort;
}
