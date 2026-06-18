package com.fjt.pojo.vo;

import lombok.Data;

@Data
public class StockVO {
    private Long id;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private String specification;
    private String unit;
    private Long warehouseId;
    private String warehouseCode;
    private String warehouseName;
    private Integer quantity;
    private Integer minStock;
}