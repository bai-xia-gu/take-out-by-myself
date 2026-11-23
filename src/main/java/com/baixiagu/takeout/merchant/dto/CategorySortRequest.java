package com.baixiagu.takeout.merchant.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategorySortRequest {

    @NotNull(message = "不能为空")
    private Integer sort;
}
