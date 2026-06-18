package com.fjt.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Inbound {
    private Long id;
    private String inboundNo;
    private Integer type;
    private Long materialId;
    private Long warehouseId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private String supplier;
    private Long operatorId;
    private String remark;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}