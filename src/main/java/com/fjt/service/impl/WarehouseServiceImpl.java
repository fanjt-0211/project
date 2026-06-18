package com.fjt.service.impl;

import com.fjt.mapper.WarehouseMapper;
import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.service.WarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public void add(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(dto, warehouse);
        warehouse.setIsDeleted(0);
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
    public Warehouse findById(Long id) {
        return warehouseMapper.findById(id);
    }

    @Override
    public Warehouse findByCode(String code) {
        return warehouseMapper.findByCode(code);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseMapper.findAll();
    }
}