package com.fjt.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InboundDTO {
    private Integer type;
    private Long materialId;
    private Long warehouseId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String supplier;
    private Long operatorId;
    private String remark;
}