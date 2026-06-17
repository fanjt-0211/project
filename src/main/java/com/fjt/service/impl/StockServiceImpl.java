package com.fjt.service.impl;

import com.fjt.mapper.MaterialMapper;
import com.fjt.mapper.StockMapper;
import com.fjt.pojo.Material;
import com.fjt.pojo.Stock;
import com.fjt.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public void add(Stock stock) {
        stockMapper.insert(stock);
    }

    @Override
    public void update(Stock stock) {
        stockMapper.update(stock);
    }

    @Override
    public void delete(Long id) {
        stockMapper.deleteById(id);
    }

    @Override
    public Stock findById(Long id) {
        return stockMapper.findById(id);
    }

    @Override
    public Stock findByMaterialAndWarehouse(Long materialId, Long warehouseId) {
        return stockMapper.findByMaterialAndWarehouse(materialId, warehouseId);
    }

    @Override
    public List<Stock> findAll() {
        return stockMapper.findAll();
    }

    @Override
    public List<Stock> findByMaterialId(Long materialId) {
        return stockMapper.findByMaterialId(materialId);
    }

    @Override
    public List<Stock> findByWarehouseId(Long warehouseId) {
        return stockMapper.findByWarehouseId(warehouseId);
    }

    @Override
    public void updateQuantity(Long materialId, Long warehouseId, Integer quantity) {
        stockMapper.updateQuantity(materialId, warehouseId, quantity);
    }

    @Override
    public void increaseStock(Long materialId, Long warehouseId, Integer quantity) {
        Stock stock = stockMapper.findByMaterialAndWarehouse(materialId, warehouseId);
        if (stock != null) {
            stockMapper.increaseStock(materialId, warehouseId, quantity);
        } else {
            Stock newStock = new Stock();
            newStock.setMaterialId(materialId);
            newStock.setWarehouseId(warehouseId);
            newStock.setQuantity(quantity);
            stockMapper.insert(newStock);
        }
    }

    @Override
    public void decreaseStock(Long materialId, Long warehouseId, Integer quantity) {
        stockMapper.decreaseStock(materialId, warehouseId, quantity);
    }

    @Override
    public List<Stock> findStockWarning() {
        List<Material> materials = materialMapper.findStockWarning();
        List<Stock> warningStocks = new ArrayList<>();
        for (Material material : materials) {
            List<Stock> stocks = stockMapper.findByMaterialId(material.getId());
            warningStocks.addAll(stocks);
        }
        return warningStocks;
    }
}