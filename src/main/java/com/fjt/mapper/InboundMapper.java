package com.fjt.mapper;

import com.fjt.pojo.Inbound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InboundMapper {
    void insert(Inbound inbound);
    void update(Inbound inbound);
    Inbound findById(Long id);
    Inbound findByInboundNo(String inboundNo);
    List<Inbound> findAll();
    List<Inbound> findByType(Integer type);
    List<Inbound> findByMaterialId(Long materialId);
    List<Inbound> findByWarehouseId(Long warehouseId);
    List<Inbound> findByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    List<Inbound> findByOperatorId(Long operatorId);
}