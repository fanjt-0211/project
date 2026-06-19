package com.fjt.controller;

import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
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

    /**
     * 查询所有分类 - 支持多条件查询
     */
    @GetMapping
    public Result<List<MaterialCategoryVO>> list(MaterialCategoryQueryDTO query) {
        return Result.success(materialCategoryService.search(query));
    }

    /**
     * 根据id查询分类
     */
    @GetMapping("/{id}")
    public Result<MaterialCategoryVO> getById(@PathVariable Long id) {
        return Result.success(materialCategoryService.findById(id));
    }

    /**
     * 添加分类
     */
    @PostMapping
    public Result<Void> add(@RequestBody MaterialCategoryDTO dto) {
        materialCategoryService.add(dto);
        return Result.success();
    }

    /**
     * 修改分类
     */
    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody MaterialCategoryDTO dto, @PathVariable Long id) {
        materialCategoryService.update(dto, id);
        return Result.success();
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialCategoryService.delete(id);
        return Result.success();
    }
}
