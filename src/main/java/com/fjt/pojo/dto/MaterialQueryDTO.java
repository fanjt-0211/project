package com.fjt.pojo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MaterialQueryDTO extends PageQueryDTO {
    private Long id;
    private String code;
    private String name;
    private String spec;
    private String unit;
    private Long categoryId;
    private Integer status;
}
