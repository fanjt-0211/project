package com.fjt.controller;

import com.fjt.pojo.Result;
import com.fjt.pojo.Stock;
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
    public Result<List<Stock>> list() {
        return Result.success(stockService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Stock> getById(@PathVariable Long id) {
        return Result.success(stockService.findById(id));
    }

    @GetMapping("/material/{materialId}")
    public Result<List<Stock>> getByMaterialId(@PathVariable Long materialId) {
        return Result.success(stockService.findByMaterialId(materialId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Result<List<Stock>> getByWarehouseId(@PathVariable Long warehouseId) {
        return Result.success(stockService.findByWarehouseId(warehouseId));
    }

    @GetMapping("/warning")
    public Result<List<Stock>> getStockWarning() {
        return Result.success(stockService.findStockWarning());
    }

    @PostMapping
    public Result<Void> add(@RequestBody Stock stock) {
        stockService.add(stock);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Stock stock) {
        stockService.update(stock);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        stockService.delete(id);
        return Result.success();
    }
}