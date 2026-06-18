package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class StockQueryDTO {
    private Long id;
    private Long materialId;
    private Long warehouseId;
    private Integer minQuantity;
}
