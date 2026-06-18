package com.fjt.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Stock {
    private Long id;
    private Long materialId;
    private Long warehouseId;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}