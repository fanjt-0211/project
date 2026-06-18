package com.fjt.service.impl;

import com.fjt.mapper.InboundMapper;
import com.fjt.mapper.MaterialMapper;
import com.fjt.mapper.UserMapper;
import com.fjt.mapper.WarehouseMapper;
import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.entity.Inbound;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.entity.User;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.pojo.vo.InboundVO;
import com.fjt.service.InboundService;
import com.fjt.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundServiceImpl implements InboundService {

    @Autowired
    private InboundMapper inboundMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void add(InboundDTO dto) {
        Inbound inbound = new Inbound();
        BeanUtils.copyProperties(dto, inbound);
        
        inbound.setInboundNo(generateInboundNo());
        
        Material material = materialMapper.findById(dto.getMaterialId());
        if (material != null && inbound.getUnitPrice() == null) {
            inbound.setUnitPrice(material.getPurchasePrice());
        }
        
        if (inbound.getTotalAmount() == null && inbound.getUnitPrice() != null) {
            inbound.setTotalAmount(inbound.getUnitPrice().multiply(BigDecimal.valueOf(inbound.getQuantity())));
        }
        
        inbound.setStatus(1);
        
        inboundMapper.insert(inbound);
        stockService.increaseStock(inbound.getMaterialId(), inbound.getWarehouseId(), inbound.getQuantity());
    }

    @Override
    public void update(Inbound inbound) {
        inboundMapper.update(inbound);
    }

    @Override
    public InboundVO findById(Long id) {
        Inbound inbound = inboundMapper.findById(id);
        return inbound != null ? convertToVO(inbound) : null;
    }

    @Override
    public InboundVO findByInboundNo(String inboundNo) {
        Inbound inbound = inboundMapper.findByInboundNo(inboundNo);
        return inbound != null ? convertToVO(inbound) : null;
    }

    @Override
    public List<InboundVO> findAll() {
        return inboundMapper.findAll().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InboundVO> search(InboundQueryDTO query) {
        return inboundMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private String generateInboundNo() {
        return "RK" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private InboundVO convertToVO(Inbound inbound) {
        InboundVO vo = new InboundVO();
        BeanUtils.copyProperties(inbound, vo);
        
        vo.setTypeName(inbound.getType() == 1 ? "采购入库" : "退货入库");
        vo.setStatusName(inbound.getStatus() == 1 ? "已完成" : "已取消");
        
        Material material = materialMapper.findById(inbound.getMaterialId());
        if (material != null) {
            vo.setMaterialName(material.getName());
            vo.setSpecification(material.getSpecification());
        }
        
        Warehouse warehouse = warehouseMapper.findById(inbound.getWarehouseId());
        if (warehouse != null) {
            vo.setWarehouseName(warehouse.getName());
        }
        
        if (inbound.getOperatorId() != null) {
            User user = userMapper.findById(inbound.getOperatorId());
            if (user != null) {
                vo.setOperatorName(user.getRealName());
            }
        }
        
        return vo;
    }
}
