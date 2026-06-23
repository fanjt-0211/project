package com.fjt.controller;

import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.MaterialCategoryVO;
import com.fjt.service.MaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class MaterialCategoryController {

    @Autowired
    private MaterialCategoryService materialCategoryService;

    /**
     * 分页查询分类 - 支持多条件查询
     */
    @GetMapping
    public Result<PageBean<MaterialCategoryVO>> list(MaterialCategoryQueryDTO query) {
        return Result.success(materialCategoryService.search(query));
    }

    /**
     * 根据id查询分类
     */
    @GetMapping("/{id}")
    public Result<MaterialCategoryVO> getById(@PathVariable Long id) {
        MaterialCategoryVO category = materialCategoryService.findById(id);
        if (category == null) {
            return Result.error("分类不存在");
        }
        return Result.success(category);
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
    @PutMapping
    public Result<Void> update(@RequestBody MaterialCategoryDTO dto) {
        materialCategoryService.update(dto, dto.getId());
        return Result.success();
    }

    /**
     * 启用/禁用分类
     */
    @PostMapping("/status/{status}")
    public Result<Void> updateStatus(@PathVariable Integer status, @RequestBody MaterialCategoryDTO dto) {
        materialCategoryService.updateStatus(dto.getId(), status);
        return Result.success();
    }
}
