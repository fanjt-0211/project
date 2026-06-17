package com.fjt.service;

import com.fjt.pojo.Stock;

import java.util.List;

public interface StockService {
    void add(Stock stock);
    void update(Stock stock);
    void delete(Long id);
    Stock findById(Long id);
    Stock findByMaterialAndWarehouse(Long materialId, Long warehouseId);
    List<Stock> findAll();
    List<Stock> findByMaterialId(Long materialId);
    List<Stock> findByWarehouseId(Long warehouseId);
    void updateQuantity(Long materialId, Long warehouseId, Integer quantity);
    void increaseStock(Long materialId, Long warehouseId, Integer quantity);
    void decreaseStock(Long materialId, Long warehouseId, Integer quantity);
    List<Stock> findStockWarning();
}