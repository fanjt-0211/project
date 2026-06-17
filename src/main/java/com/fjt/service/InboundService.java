package com.fjt.service;

import com.fjt.pojo.Inbound;

import java.time.LocalDateTime;
import java.util.List;

public interface InboundService {
    void add(Inbound inbound);
    void update(Inbound inbound);
    Inbound findById(Long id);
    Inbound findByInboundNo(String inboundNo);
    List<Inbound> findAll();
    List<Inbound> findByType(Integer type);
    List<Inbound> findByMaterialId(Long materialId);
    List<Inbound> findByWarehouseId(Long warehouseId);
    List<Inbound> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    List<Inbound> findByOperatorId(Long operatorId);
}