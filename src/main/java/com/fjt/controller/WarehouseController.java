package com.fjt.controller;

import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.dto.WarehouseQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.WarehouseVO;
import com.fjt.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    /**
     * 查询仓库列表
     */
    @GetMapping
    public Result<List<WarehouseVO>> list() {
        return Result.success(warehouseService.findAll());
    }

    /**
     * 根据id查询仓库
     */
    @GetMapping("/{id}")
    public Result<WarehouseVO> getById(@PathVariable Long id) {
        return Result.success(null);
    }

    /**
     * 通用查询接口 - 支持多条件模糊查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<WarehouseVO>> search(WarehouseQueryDTO query) {
        return Result.success(warehouseService.search(query));
    }

    /**
     * 添加仓库
     */
    @PostMapping
    public Result<Void> add(@RequestBody WarehouseDTO dto) {
        warehouseService.add(dto);
        return Result.success();
    }

    /**
     * 修改仓库
     */
    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody WarehouseDTO dto, @PathVariable Long id) {
        warehouseService.update(dto, id);
        return Result.success();
    }

    /**
     * 删除仓库
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return Result.success();
    }
}
