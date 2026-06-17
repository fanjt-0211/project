package com.fjt.controller;

import com.fjt.pojo.Outbound;
import com.fjt.pojo.Result;
import com.fjt.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    @GetMapping
    public Result<List<Outbound>> list() {
        return Result.success(outboundService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Outbound> getById(@PathVariable Long id) {
        return Result.success(outboundService.findById(id));
    }

    @GetMapping("/type/{type}")
    public Result<List<Outbound>> getByType(@PathVariable Integer type) {
        return Result.success(outboundService.findByType(type));
    }

    @GetMapping("/material/{materialId}")
    public Result<List<Outbound>> getByMaterialId(@PathVariable Long materialId) {
        return Result.success(outboundService.findByMaterialId(materialId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Result<List<Outbound>> getByWarehouseId(@PathVariable Long warehouseId) {
        return Result.success(outboundService.findByWarehouseId(warehouseId));
    }

    @GetMapping("/time-range")
    public Result<List<Outbound>> getByTimeRange(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return Result.success(outboundService.findByTimeRange(startTime, endTime));
    }

    @PostMapping
    public Result<Void> add(@RequestBody Outbound outbound) {
        outboundService.add(outbound);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Outbound outbound) {
        outboundService.update(outbound);
        return Result.success();
    }
}