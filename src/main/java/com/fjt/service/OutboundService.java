package com.fjt.service;

import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.entity.Outbound;
import com.fjt.pojo.vo.OutboundVO;

import java.time.LocalDateTime;
import java.util.List;

public interface OutboundService {
    void add(OutboundDTO dto);
    void update(Outbound outbound);
    OutboundVO findById(Long id);
    OutboundVO findByOutboundNo(String outboundNo);
    List<OutboundVO> findAll();
    List<OutboundVO> findByType(Integer type);
    List<OutboundVO> findByMaterialId(Long materialId);
    List<OutboundVO> findByWarehouseId(Long warehouseId);
    List<OutboundVO> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    List<OutboundVO> findByOperatorId(Long operatorId);
}