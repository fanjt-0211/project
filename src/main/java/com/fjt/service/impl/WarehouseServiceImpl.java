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
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(dto, warehouse);
        warehouse.setStatus(1);
        warehouseMapper.insert(warehouse);
    }

    @Override
    public void update(WarehouseDTO dto, Long id) {
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(dto, warehouse);
        warehouse.setId(id);
        warehouseMapper.update(warehouse);
    }

    @Override
    public void delete(Long id) {
        warehouseMapper.deleteById(id);
    }

    @Override
    public WarehouseVO findById(Long id) {
        Warehouse warehouse = warehouseMapper.findById(id);
        return warehouse != null ? convertToVO(warehouse) : null;
    }

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
