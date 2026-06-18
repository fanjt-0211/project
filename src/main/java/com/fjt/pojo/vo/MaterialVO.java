package com.fjt.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MaterialVO {
    private Long id;
    private String code;
    private String name;
    private Long categoryId;
    private String categoryName;
    private String specification;
    private String unit;
    private BigDecimal purchasePrice;
    private BigDecimal sellPrice;
    private Integer minStock;
    private Integer maxStock;
    private String description;
    private LocalDateTime createdAt;
}