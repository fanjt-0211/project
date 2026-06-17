package com.fjt.controller;

import com.fjt.pojo.MaterialCategory;
import com.fjt.pojo.Result;
import com.fjt.service.MaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class MaterialCategoryController {

    @Autowired
    private MaterialCategoryService materialCategoryService;

    @GetMapping
    public Result<List<MaterialCategory>> list() {
        return Result.success(materialCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public Result<MaterialCategory> getById(@PathVariable Long id) {
        return Result.success(materialCategoryService.findById(id));
    }

    @GetMapping("/parent/{parentId}")
    public Result<List<MaterialCategory>> getByParentId(@PathVariable Long parentId) {
        return Result.success(materialCategoryService.findByParentId(parentId));
    }

    @PostMapping
    public Result<Void> add(@RequestBody MaterialCategory category) {
        materialCategoryService.add(category);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody MaterialCategory category) {
        materialCategoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialCategoryService.delete(id);
        return Result.success();
    }
}