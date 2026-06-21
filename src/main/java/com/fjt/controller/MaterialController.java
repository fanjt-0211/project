package com.fjt.controller;

import com.fjt.annotation.RequireAdmin;
import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.dto.MaterialQueryDTO;
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

    /**
     * 分页查询物料 - 支持动态条件查询
     */
    @GetMapping
    public Result<PageBean<MaterialVO>> list(MaterialQueryDTO query) {
        return Result.success(materialService.list(query));
    }

    /**
     * 根据id查询物料
     */
    @GetMapping("/{id}")
    public Result<MaterialVO> getById(@PathVariable Long id) {
        return Result.success(materialService.findById(id));
    }

    /**
     * 查询库存预警物料
     */
    @GetMapping("/warning")
    public Result<List<MaterialVO>> getStockWarning() {
        return Result.success(materialService.findStockWarning());
    }

    /**
     * 添加物料
     */
    @PostMapping
    public Result<Void> add(@RequestBody MaterialDTO dto) {
        materialService.add(dto);
        return Result.success();
    }

    /**
     * 修改物料
     */
    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody MaterialDTO dto, @PathVariable Long id) {
        materialService.update(dto, id);
        return Result.success();
    }
}
