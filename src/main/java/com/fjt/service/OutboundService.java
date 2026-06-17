package com.fjt.service;

import com.fjt.pojo.Outbound;

import java.time.LocalDateTime;
import java.util.List;

public interface OutboundService {
    void add(Outbound outbound);
    void update(Outbound outbound);
    Outbound findById(Long id);
    Outbound findByOutboundNo(String outboundNo);
    List<Outbound> findAll();
    List<Outbound> findByType(Integer type);
    List<Outbound> findByMaterialId(Long materialId);
    List<Outbound> findByWarehouseId(Long warehouseId);
    List<Outbound> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    List<Outbound> findByOperatorId(Long operatorId);
}