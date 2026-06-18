package com.fjt.controller;

import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public Result<List<Warehouse>> list() {
        return Result.success(warehouseService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Warehouse> getById(@PathVariable Long id) {
        return Result.success(warehouseService.findById(id));
    }

    @PostMapping
    public Result<Void> add(@RequestBody WarehouseDTO dto) {
        warehouseService.add(dto);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody WarehouseDTO dto, @PathVariable Long id) {
        warehouseService.update(dto, id);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return Result.success();
    }
}