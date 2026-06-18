package com.fjt.mapper;

import com.fjt.pojo.entity.Outbound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OutboundMapper {
    void insert(Outbound outbound);
    void update(Outbound outbound);
    Outbound findById(Long id);
    Outbound findByOutboundNo(String outboundNo);
    List<Outbound> findAll();
    List<Outbound> findByType(Integer type);
    List<Outbound> findByMaterialId(Long materialId);
    List<Outbound> findByWarehouseId(Long warehouseId);
    List<Outbound> findByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    List<Outbound> findByOperatorId(Long operatorId);
}