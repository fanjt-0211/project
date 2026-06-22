package com.fjt.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InboundVO {
    private Long id;
    private String inboundNo;
    private Integer type;
    private String typeName;
    private Long materialId;
    private String materialName;
    private String specification;
    private Long warehouseId;
    private String warehouseName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private String supplier;
    private Long operatorId;
    private String operatorName;
    private String remark;
    private Integer status;
    private String statusName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;
}