package com.fjt.service;

import com.fjt.pojo.Warehouse;

import java.util.List;

public interface WarehouseService {
    void add(Warehouse warehouse);
    void update(Warehouse warehouse);
    void delete(Long id);
    Warehouse findById(Long id);
    Warehouse findByCode(String code);
    List<Warehouse> findAll();
}