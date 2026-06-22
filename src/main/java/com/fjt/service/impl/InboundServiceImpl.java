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
import com.fjt.pojo.PageBean;
import com.fjt.utils.UserHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        Material material = materialMapper.findById(dto.getMaterialId());
        if (material == null) {
            throw new RuntimeException("物资不存在");
        }
        
        Warehouse warehouse = warehouseMapper.findById(dto.getWarehouseId());
        if (warehouse == null) {
            throw new RuntimeException("仓库不存在");
        }
        
        Inbound inbound = new Inbound();
        BeanUtils.copyProperties(dto, inbound);
        
        inbound.setInboundNo(generateInboundNo());
        
        if (inbound.getUnitPrice() == null) {
            inbound.setUnitPrice(material.getPurchasePrice());
        }
        
        if (inbound.getTotalAmount() == null && inbound.getUnitPrice() != null) {
            inbound.setTotalAmount(inbound.getUnitPrice().multiply(BigDecimal.valueOf(inbound.getQuantity())));
        }
        
        inbound.setStatus(1);
        inbound.setOperatorId(UserHolder.getUserId());
        
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

    /**
     * 通用查询接口 - 支持多条件分页查询
     */
    @Override
    public PageBean<InboundVO> list(InboundQueryDTO query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Inbound> list = inboundMapper.search(query);
        PageInfo<Inbound> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        List<InboundVO> records = pageInfo.getList().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return new PageBean<>(total, records);
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
