package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class WarehouseDTO {
    private Long id;
    private String code;
    private String name;
    private String location;
    private Integer capacity;
    private String description;
}