package com.fjt.pojo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InboundQueryDTO {
    private Long id;
    private String inboundNo;
    private Integer type;
    private Long materialId;
    private Long warehouseId;
    private Long operatorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
