package com.fjt.controller;

import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.OutboundVO;
import com.fjt.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    @GetMapping
    public Result<List<OutboundVO>> list() {
        return Result.success(outboundService.findAll());
    }

    @GetMapping("/{id}")
    public Result<OutboundVO> getById(@PathVariable Long id) {
        return Result.success(outboundService.findById(id));
    }

    @GetMapping("/type/{type}")
    public Result<List<OutboundVO>> getByType(@PathVariable Integer type) {
        return Result.success(outboundService.findByType(type));
    }

    @GetMapping("/material/{materialId}")
    public Result<List<OutboundVO>> getByMaterialId(@PathVariable Long materialId) {
        return Result.success(outboundService.findByMaterialId(materialId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Result<List<OutboundVO>> getByWarehouseId(@PathVariable Long warehouseId) {
        return Result.success(outboundService.findByWarehouseId(warehouseId));
    }

    @GetMapping("/time-range")
    public Result<List<OutboundVO>> getByTimeRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return Result.success(outboundService.findByTimeRange(startTime, endTime));
    }

    @PostMapping
    public Result<Void> add(@RequestBody OutboundDTO dto) {
        outboundService.add(dto);
        return Result.success();
    }
}