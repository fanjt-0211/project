package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class MaterialCategoryDTO {
    private String name;
    private String description;
    private Long parentId;
    private Integer sortOrder;
}