package com.fjt.service.impl;

import com.fjt.mapper.MaterialMapper;
import com.fjt.mapper.OutboundMapper;
import com.fjt.mapper.UserMapper;
import com.fjt.mapper.WarehouseMapper;
import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.dto.OutboundQueryDTO;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.entity.Outbound;
import com.fjt.pojo.entity.Stock;
import com.fjt.pojo.entity.User;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.pojo.vo.OutboundVO;
import com.fjt.service.OutboundService;
import com.fjt.service.StockService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class OutboundServiceImpl implements OutboundService {

    @Autowired
    private OutboundMapper outboundMapper;

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
    public void add(OutboundDTO dto) {
        Outbound outbound = new Outbound();
        BeanUtils.copyProperties(dto, outbound);
        
        Stock stock = stockService.findByMaterialAndWarehouse(dto.getMaterialId(), dto.getWarehouseId());
        if (stock == null || stock.getQuantity() < outbound.getQuantity()) {
            throw new RuntimeException("库存不足");
        }
        
        outbound.setOutboundNo(generateOutboundNo());
        
        Material material = materialMapper.findById(dto.getMaterialId());
        if (material != null && outbound.getUnitPrice() == null) {
            outbound.setUnitPrice(material.getSellPrice());
        }
        
        if (outbound.getTotalAmount() == null && outbound.getUnitPrice() != null) {
            outbound.setTotalAmount(outbound.getUnitPrice().multiply(BigDecimal.valueOf(outbound.getQuantity())));
        }
        
        outbound.setStatus(1);
        
        outboundMapper.insert(outbound);
        stockService.decreaseStock(outbound.getMaterialId(), outbound.getWarehouseId(), outbound.getQuantity());
    }

    @Override
    public void update(Outbound outbound) {
        outboundMapper.update(outbound);
    }

    @Override
    public OutboundVO findById(Long id) {
        Outbound outbound = outboundMapper.findById(id);
        return outbound != null ? convertToVO(outbound) : null;
    }

    @Override
    public OutboundVO findByOutboundNo(String outboundNo) {
        Outbound outbound = outboundMapper.findByOutboundNo(outboundNo);
        return outbound != null ? convertToVO(outbound) : null;
    }

    /**
     * 通用查询接口 - 支持多条件分页查询
     * 参数可为空，为空则查询所有
     */
    @Override
    public PageBean<OutboundVO> list(OutboundQueryDTO query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Outbound> list = outboundMapper.search(query);
        Page<Outbound> pageResult = (Page<Outbound>) list;
        long total = pageResult.getTotal();
        List<OutboundVO> records = pageResult.getResult().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return new PageBean<>(total, records);
    }

    private String generateOutboundNo() {
        return "CK" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private OutboundVO convertToVO(Outbound outbound) {
        OutboundVO vo = new OutboundVO();
        BeanUtils.copyProperties(outbound, vo);
        
        vo.setTypeName(outbound.getType() == 1 ? "领用出库" : "报废出库");
        vo.setStatusName(outbound.getStatus() == 1 ? "已完成" : "已取消");
        
        Material material = materialMapper.findById(outbound.getMaterialId());
        if (material != null) {
            vo.setMaterialName(material.getName());
            vo.setSpecification(material.getSpecification());
        }
        
        Warehouse warehouse = warehouseMapper.findById(outbound.getWarehouseId());
        if (warehouse != null) {
            vo.setWarehouseName(warehouse.getName());
        }
        
        if (outbound.getOperatorId() != null) {
            User user = userMapper.findById(outbound.getOperatorId());
            if (user != null) {
                vo.setOperatorName(user.getRealName());
            }
        }
        
        return vo;
    }
}
