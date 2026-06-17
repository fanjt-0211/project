package com.fjt.mapper;

import com.fjt.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
    void insert(Stock stock);
    void update(Stock stock);
    void deleteById(Long id);
    Stock findById(Long id);
    Stock findByMaterialAndWarehouse(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId);
    List<Stock> findAll();
    List<Stock> findByMaterialId(Long materialId);
    List<Stock> findByWarehouseId(Long warehouseId);
    void updateQuantity(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId, @Param("quantity") Integer quantity);
    void increaseStock(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId, @Param("quantity") Integer quantity);
    void decreaseStock(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId, @Param("quantity") Integer quantity);
}