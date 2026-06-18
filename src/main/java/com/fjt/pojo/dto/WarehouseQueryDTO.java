package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class WarehouseQueryDTO {
    private Long id;
    private String code;
    private String name;
    private String location;
    private Integer status;
}
