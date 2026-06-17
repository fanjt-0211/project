package com.fjt.controller;

import com.fjt.pojo.Material;
import com.fjt.pojo.Result;
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
    public Result<List<Material>> list() {
        return Result.success(materialService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Material> getById(@PathVariable Long id) {
        return Result.success(materialService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public Result<List<Material>> getByCategoryId(@PathVariable Long categoryId) {
        return Result.success(materialService.findByCategoryId(categoryId));
    }

    @GetMapping("/search")
    public Result<List<Material>> search(@RequestParam String keyword) {
        return Result.success(materialService.search(keyword));
    }

    @GetMapping("/warning")
    public Result<List<Material>> getStockWarning() {
        return Result.success(materialService.findStockWarning());
    }

    @PostMapping
    public Result<Void> add(@RequestBody Material material) {
        materialService.add(material);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Material material) {
        materialService.update(material);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return Result.success();
    }
}