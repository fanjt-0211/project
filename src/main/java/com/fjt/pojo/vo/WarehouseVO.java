package com.fjt.pojo.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WarehouseVO {
    private Long id;
    private String code;
    private String name;
    private String location;
    private Integer capacity;
    private String description;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
