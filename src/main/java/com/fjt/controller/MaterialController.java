package com.fjt.controller;

import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.MaterialVO;
import com.fjt.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public Result<List<MaterialVO>> list() {
        return Result.success(materialService.findAll());
    }

    @GetMapping("/{id}")
    public Result<MaterialVO> getById(@PathVariable Long id) {
        return Result.success(materialService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public Result<List<MaterialVO>> getByCategoryId(@PathVariable Long categoryId) {
        return Result.success(materialService.findByCategoryId(categoryId));
    }

    @GetMapping("/search")
    public Result<List<MaterialVO>> search(@RequestParam String keyword) {
        return Result.success(materialService.search(keyword));
    }

    @GetMapping("/warning")
    public Result<List<MaterialVO>> getStockWarning() {
        return Result.success(materialService.findStockWarning());
    }

    @PostMapping
    public Result<Void> add(@RequestBody MaterialDTO dto) {
        materialService.add(dto);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody MaterialDTO dto, @PathVariable Long id) {
        materialService.update(dto, id);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return Result.success();
    }
}