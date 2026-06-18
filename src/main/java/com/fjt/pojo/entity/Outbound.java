package com.fjt.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Outbound {
    private Long id;
    private String outboundNo;
    private Integer type;
    private Long materialId;
    private Long warehouseId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private String recipient;
    private String department;
    private Long operatorId;
    private String remark;
    private Integer status;
    private LocalDateTime createdAt;
}