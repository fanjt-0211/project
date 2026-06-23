package com.fjt.service;

import com.fjt.pojo.dto.StockQueryDTO;
import com.fjt.pojo.entity.Stock;
import com.fjt.pojo.vo.StockVO;

import com.fjt.pojo.PageBean;
import java.util.List;

public interface StockService {
    void add(Stock stock);
    void update(Stock stock);
    StockVO findById(Long id);
    Stock findByMaterialAndWarehouse(Long materialId, Long warehouseId);
    PageBean<StockVO> list(StockQueryDTO query);
    void increaseStock(Long materialId, Long warehouseId, Integer quantity);
    void decreaseStock(Long materialId, Long warehouseId, Integer quantity);
    List<StockVO> findStockWarning();
    int countByWarehouseId(Long warehouseId);
}
