package com.fjt.controller;

import com.fjt.pojo.PageBean;
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
     * 分页查询库存 - 支持动态条件查询
     */
    @GetMapping
    public Result<PageBean<StockVO>> list(StockQueryDTO query) {
        return Result.success(stockService.list(query));
    }

    /**
     * 根据id查询库存
     */
    @GetMapping("/{id}")
    public Result<StockVO> getById(@PathVariable Long id) {
        StockVO stock = stockService.findById(id);
        if (stock == null) {
            return Result.error("库存记录不存在");
        }
        return Result.success(stock);
    }

    /**
     * 查询库存预警
     */
    @GetMapping("/warning")
    public Result<List<StockVO>> getStockWarning() {
        return Result.success(stockService.findStockWarning());
    }
}
