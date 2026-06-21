package com.fjt.pojo.vo;

import lombok.Data;

@Data
public class MaterialCategoryVO {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private String parentName;
    private Integer sortOrder;
    private Integer status;
}