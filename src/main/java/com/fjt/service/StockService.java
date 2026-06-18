package com.fjt.service;

import com.fjt.pojo.entity.Stock;
import com.fjt.pojo.vo.StockVO;

import java.util.List;

public interface StockService {
    void add(Stock stock);
    void update(Stock stock);
    void delete(Long id);
    StockVO findById(Long id);
    Stock findByMaterialAndWarehouse(Long materialId, Long warehouseId);
    List<StockVO> findAll();
    List<StockVO> findByMaterialId(Long materialId);
    List<StockVO> findByWarehouseId(Long warehouseId);
    void updateQuantity(Long materialId, Long warehouseId, Integer quantity);
    void increaseStock(Long materialId, Long warehouseId, Integer quantity);
    void decreaseStock(Long materialId, Long warehouseId, Integer quantity);
    List<StockVO> findStockWarning();
}