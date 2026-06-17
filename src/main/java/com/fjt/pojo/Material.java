package com.fjt.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Material {
    private Long id;
    private String code;
    private String name;
    private Long categoryId;
    private String specification;
    private String unit;
    private BigDecimal purchasePrice;
    private BigDecimal sellPrice;
    private Integer minStock;
    private Integer maxStock;
    private String description;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}