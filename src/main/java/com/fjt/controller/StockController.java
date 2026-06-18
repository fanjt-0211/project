package com.fjt.controller;

import com.fjt.pojo.dto.StockQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.StockVO;
import com.fjt.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 查询所有库存
     */
    @GetMapping
    public Result<List<StockVO>> list() {
        return Result.success(stockService.findAll());
    }

    /**
     * 根据id查询库存
     */
    @GetMapping("/{id}")
    public Result<StockVO> getById(@PathVariable Long id) {
        return Result.success(stockService.findById(id));
    }

    /**
     * 通用查询接口 - 支持多条件查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<StockVO>> search(StockQueryDTO query) {
        return Result.success(stockService.search(query));
    }

    /**
     * 查询库存预警
     */
    @GetMapping("/warning")
    public Result<List<StockVO>> getStockWarning() {
        return Result.success(stockService.findStockWarning());
    }

    /**
     * 删除库存
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        stockService.delete(id);
        return Result.success();
    }
}
