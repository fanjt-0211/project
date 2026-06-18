package com.fjt.service.impl;

import com.fjt.mapper.MaterialMapper;
import com.fjt.mapper.StockMapper;
import com.fjt.mapper.WarehouseMapper;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.entity.Stock;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.pojo.vo.StockVO;
import com.fjt.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

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
    public StockVO findById(Long id) {
        Stock stock = stockMapper.findById(id);
        return stock != null ? convertToVO(stock) : null;
    }

    @Override
    public Stock findByMaterialAndWarehouse(Long materialId, Long warehouseId) {
        return stockMapper.findByMaterialAndWarehouse(materialId, warehouseId);
    }

    @Override
    public List<StockVO> findAll() {
        return stockMapper.findAll().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockVO> findByMaterialId(Long materialId) {
        return stockMapper.findByMaterialId(materialId).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockVO> findByWarehouseId(Long warehouseId) {
        return stockMapper.findByWarehouseId(warehouseId).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
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
    public List<StockVO> findStockWarning() {
        List<Material> materials = materialMapper.findStockWarning();
        List<Stock> warningStocks = new ArrayList<>();
        for (Material material : materials) {
            List<Stock> stocks = stockMapper.findByMaterialId(material.getId());
            warningStocks.addAll(stocks);
        }
        return warningStocks.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private StockVO convertToVO(Stock stock) {
        StockVO vo = new StockVO();
        BeanUtils.copyProperties(stock, vo);
        
        Material material = materialMapper.findById(stock.getMaterialId());
        if (material != null) {
            vo.setMaterialCode(material.getCode());
            vo.setMaterialName(material.getName());
            vo.setSpecification(material.getSpecification());
            vo.setUnit(material.getUnit());
            vo.setMinStock(material.getMinStock());
        }
        
        Warehouse warehouse = warehouseMapper.findById(stock.getWarehouseId());
        if (warehouse != null) {
            vo.setWarehouseCode(warehouse.getCode());
            vo.setWarehouseName(warehouse.getName());
        }
        
        return vo;
    }
}