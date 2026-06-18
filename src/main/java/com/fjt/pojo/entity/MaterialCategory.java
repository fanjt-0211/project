package com.fjt.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialCategory {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private Integer sortOrder;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}