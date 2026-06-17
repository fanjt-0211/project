package com.fjt.service.impl;

import com.fjt.mapper.MaterialMapper;
import com.fjt.mapper.OutboundMapper;
import com.fjt.pojo.Material;
import com.fjt.pojo.Outbound;
import com.fjt.pojo.Stock;
import com.fjt.service.OutboundService;
import com.fjt.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OutboundServiceImpl implements OutboundService {

    @Autowired
    private OutboundMapper outboundMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    @Transactional
    public void add(Outbound outbound) {
        outbound.setOutboundNo(generateOutboundNo());
        
        Stock stock = stockService.findByMaterialAndWarehouse(outbound.getMaterialId(), outbound.getWarehouseId());
        if (stock == null || stock.getQuantity() < outbound.getQuantity()) {
            throw new RuntimeException("库存不足");
        }
        
        Material material = materialMapper.findById(outbound.getMaterialId());
        if (material != null && outbound.getUnitPrice() == null) {
            outbound.setUnitPrice(material.getSellPrice());
        }
        
        if (outbound.getTotalAmount() == null && outbound.getUnitPrice() != null) {
            outbound.setTotalAmount(outbound.getUnitPrice().multiply(BigDecimal.valueOf(outbound.getQuantity())));
        }
        
        outboundMapper.insert(outbound);
        stockService.decreaseStock(outbound.getMaterialId(), outbound.getWarehouseId(), outbound.getQuantity());
    }

    @Override
    public void update(Outbound outbound) {
        outboundMapper.update(outbound);
    }

    @Override
    public Outbound findById(Long id) {
        return outboundMapper.findById(id);
    }

    @Override
    public Outbound findByOutboundNo(String outboundNo) {
        return outboundMapper.findByOutboundNo(outboundNo);
    }

    @Override
    public List<Outbound> findAll() {
        return outboundMapper.findAll();
    }

    @Override
    public List<Outbound> findByType(Integer type) {
        return outboundMapper.findByType(type);
    }

    @Override
    public List<Outbound> findByMaterialId(Long materialId) {
        return outboundMapper.findByMaterialId(materialId);
    }

    @Override
    public List<Outbound> findByWarehouseId(Long warehouseId) {
        return outboundMapper.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Outbound> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return outboundMapper.findByTimeRange(startTime, endTime);
    }

    @Override
    public List<Outbound> findByOperatorId(Long operatorId) {
        return outboundMapper.findByOperatorId(operatorId);
    }

    private String generateOutboundNo() {
        return "CK" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}