package com.fjt.service;

import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    void add(WarehouseDTO dto);
    void update(WarehouseDTO dto, Long id);
    void delete(Long id);
    Warehouse findById(Long id);
    Warehouse findByCode(String code);
    List<Warehouse> findAll();
}