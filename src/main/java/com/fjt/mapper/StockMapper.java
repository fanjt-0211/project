package com.fjt.mapper;

import com.fjt.pojo.dto.StockQueryDTO;
import com.fjt.pojo.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
    void insert(Stock stock);
    void update(Stock stock);
    Stock findById(Long id);
    Stock findByMaterialAndWarehouse(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId);
    List<Stock> search(StockQueryDTO query);
    void increaseStock(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId, @Param("quantity") Integer quantity);
    void decreaseStock(@Param("materialId") Long materialId, @Param("warehouseId") Long warehouseId, @Param("quantity") Integer quantity);

    /**
     * 查询库存预警：库存量低于最低库存阈值的库存记录
     */
    List<Stock> findStockWarning();
}
