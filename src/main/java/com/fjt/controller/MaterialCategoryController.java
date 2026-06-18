package com.fjt.controller;

import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.MaterialCategoryVO;
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
    public Result<List<MaterialCategoryVO>> list() {
        return Result.success(materialCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public Result<MaterialCategoryVO> getById(@PathVariable Long id) {
        return Result.success(materialCategoryService.findById(id));
    }

    @GetMapping("/parent/{parentId}")
    public Result<List<MaterialCategoryVO>> getByParentId(@PathVariable Long parentId) {
        return Result.success(materialCategoryService.findByParentId(parentId));
    }

    @GetMapping("/search")
    public Result<List<MaterialCategoryVO>> search(@RequestParam String name) {
        return Result.success(materialCategoryService.findByName(name));
    }

    @PostMapping
    public Result<Void> add(@RequestBody MaterialCategoryDTO dto) {
        materialCategoryService.add(dto);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody MaterialCategoryDTO dto, @PathVariable Long id) {
        materialCategoryService.update(dto, id);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialCategoryService.delete(id);
        return Result.success();
    }
}