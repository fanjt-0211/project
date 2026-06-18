package com.fjt.controller;

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
     * 查询所有物料
     */
    @GetMapping
    public Result<List<MaterialVO>> list() {
        return Result.success(materialService.findAll());
    }

    /**
     * 根据id查询物料
     */
    @GetMapping("/{id}")
    public Result<MaterialVO> getById(@PathVariable Long id) {
        return Result.success(materialService.findById(id));
    }

    /**
     * 通用查询接口 - 支持多条件模糊查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<MaterialVO>> search(MaterialQueryDTO query) {
        return Result.success(materialService.search(query));
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

    /**
     * 删除物料
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return Result.success();
    }
}
