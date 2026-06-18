package com.fjt.service;

import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.entity.Inbound;
import com.fjt.pojo.vo.InboundVO;

import java.time.LocalDateTime;
import java.util.List;

public interface InboundService {
    void add(InboundDTO dto);
    void update(Inbound inbound);
    InboundVO findById(Long id);
    InboundVO findByInboundNo(String inboundNo);
    List<InboundVO> findAll();
    List<InboundVO> findByType(Integer type);
    List<InboundVO> findByMaterialId(Long materialId);
    List<InboundVO> findByWarehouseId(Long warehouseId);
    List<InboundVO> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    List<InboundVO> findByOperatorId(Long operatorId);
}