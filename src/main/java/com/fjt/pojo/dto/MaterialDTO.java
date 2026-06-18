package com.fjt.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialDTO {
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
}