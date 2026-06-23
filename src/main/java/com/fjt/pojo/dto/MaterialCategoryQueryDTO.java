package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class MaterialCategoryQueryDTO extends PageQueryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer status;
}
