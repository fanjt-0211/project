package com.fjt.service.impl;

import com.fjt.mapper.InboundMapper;
import com.fjt.mapper.MaterialMapper;
import com.fjt.pojo.Inbound;
import com.fjt.pojo.Material;
import com.fjt.service.InboundService;
import com.fjt.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InboundServiceImpl implements InboundService {

    @Autowired
    private InboundMapper inboundMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    @Transactional
    public void add(Inbound inbound) {
        inbound.setInboundNo(generateInboundNo());
        
        Material material = materialMapper.findById(inbound.getMaterialId());
        if (material != null && inbound.getUnitPrice() == null) {
            inbound.setUnitPrice(material.getPurchasePrice());
        }
        
        if (inbound.getTotalAmount() == null && inbound.getUnitPrice() != null) {
            inbound.setTotalAmount(inbound.getUnitPrice().multiply(BigDecimal.valueOf(inbound.getQuantity())));
        }
        
        inboundMapper.insert(inbound);
        stockService.increaseStock(inbound.getMaterialId(), inbound.getWarehouseId(), inbound.getQuantity());
    }

    @Override
    public void update(Inbound inbound) {
        inboundMapper.update(inbound);
    }

    @Override
    public Inbound findById(Long id) {
        return inboundMapper.findById(id);
    }

    @Override
    public Inbound findByInboundNo(String inboundNo) {
        return inboundMapper.findByInboundNo(inboundNo);
    }

    @Override
    public List<Inbound> findAll() {
        return inboundMapper.findAll();
    }

    @Override
    public List<Inbound> findByType(Integer type) {
        return inboundMapper.findByType(type);
    }

    @Override
    public List<Inbound> findByMaterialId(Long materialId) {
        return inboundMapper.findByMaterialId(materialId);
    }

    @Override
    public List<Inbound> findByWarehouseId(Long warehouseId) {
        return inboundMapper.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Inbound> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return inboundMapper.findByTimeRange(startTime, endTime);
    }

    @Override
    public List<Inbound> findByOperatorId(Long operatorId) {
        return inboundMapper.findByOperatorId(operatorId);
    }

    private String generateInboundNo() {
        return "RK" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}