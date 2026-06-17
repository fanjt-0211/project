package com.fjt.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Warehouse {
    private Long id;
    private String code;
    private String name;
    private String location;
    private Integer capacity;
    private String description;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}