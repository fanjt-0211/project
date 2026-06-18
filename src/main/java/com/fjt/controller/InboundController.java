package com.fjt.controller;

import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.InboundVO;
import com.fjt.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/inbound")
public class InboundController {

    @Autowired
    private InboundService inboundService;

    @GetMapping
    public Result<List<InboundVO>> list() {
        return Result.success(inboundService.findAll());
    }

    @GetMapping("/{id}")
    public Result<InboundVO> getById(@PathVariable Long id) {
        return Result.success(inboundService.findById(id));
    }

    @GetMapping("/type/{type}")
    public Result<List<InboundVO>> getByType(@PathVariable Integer type) {
        return Result.success(inboundService.findByType(type));
    }

    @GetMapping("/material/{materialId}")
    public Result<List<InboundVO>> getByMaterialId(@PathVariable Long materialId) {
        return Result.success(inboundService.findByMaterialId(materialId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Result<List<InboundVO>> getByWarehouseId(@PathVariable Long warehouseId) {
        return Result.success(inboundService.findByWarehouseId(warehouseId));
    }

    @GetMapping("/time-range")
    public Result<List<InboundVO>> getByTimeRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return Result.success(inboundService.findByTimeRange(startTime, endTime));
    }

    @PostMapping
    public Result<Void> add(@RequestBody InboundDTO dto) {
        inboundService.add(dto);
        return Result.success();
    }
}