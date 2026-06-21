package com.fjt.service.impl;

import com.fjt.mapper.WarehouseMapper;
import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.dto.WarehouseQueryDTO;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.pojo.vo.WarehouseVO;
import com.fjt.service.WarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public void add(WarehouseDTO dto) {
        // 校验仓库编码是否重复
        if (warehouseMapper.findByCode(dto.getCode()) != null) {
            throw new RuntimeException("仓库编码已存在");
        }
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(dto, warehouse);
        warehouse.setStatus(1);
        warehouseMapper.insert(warehouse);
    }

    /**
     * 修改仓库
     */
    @Override
    public void update(WarehouseDTO dto, Long id) {
        // 校验仓库编码是否与其他记录重复
        Warehouse existing = warehouseMapper.findByCode(dto.getCode());
        if (existing != null && !existing.getId().equals(id)) {
            throw new RuntimeException("仓库编码已存在");
        }
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(dto, warehouse);
        warehouse.setId(id);
        warehouseMapper.update(warehouse);
    }

    /**
     * 根据id查询仓库
     */
    @Override
    public WarehouseVO findById(Long id) {
        Warehouse warehouse = warehouseMapper.findById(id);
        return warehouse != null ? convertToVO(warehouse) : null;
    }

    /**
     * 根据编码查询仓库
     */
    @Override
    public Warehouse findByCode(String code) {
        return warehouseMapper.findByCode(code);
    }

    @Override
    public List<WarehouseVO> search(WarehouseQueryDTO query) {
        return warehouseMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private WarehouseVO convertToVO(Warehouse warehouse) {
        WarehouseVO vo = new WarehouseVO();
        BeanUtils.copyProperties(warehouse, vo);
        return vo;
    }
}
