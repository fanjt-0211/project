package com.fjt.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OutboundDTO {
    private Integer type;
    private Long materialId;
    private Long warehouseId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String recipient;
    private String department;
    private Long operatorId;
    private String remark;
}