package com.fjt.controller;

import com.fjt.pojo.Result;
import com.fjt.pojo.vo.StockVO;
import com.fjt.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public Result<List<StockVO>> list() {
        return Result.success(stockService.findAll());
    }

    @GetMapping("/{id}")
    public Result<StockVO> getById(@PathVariable Long id) {
        return Result.success(stockService.findById(id));
    }

    @GetMapping("/material/{materialId}")
    public Result<List<StockVO>> getByMaterialId(@PathVariable Long materialId) {
        return Result.success(stockService.findByMaterialId(materialId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Result<List<StockVO>> getByWarehouseId(@PathVariable Long warehouseId) {
        return Result.success(stockService.findByWarehouseId(warehouseId));
    }

    @GetMapping("/warning")
    public Result<List<StockVO>> getStockWarning() {
        return Result.success(stockService.findStockWarning());
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        stockService.delete(id);
        return Result.success();
    }
}