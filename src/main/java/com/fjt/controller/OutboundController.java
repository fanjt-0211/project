package com.fjt.controller;

import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.dto.OutboundQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.OutboundVO;
import com.fjt.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    /**
     * 查询所有出库单
     */
    @GetMapping
    public Result<List<OutboundVO>> list() {
        return Result.success(outboundService.findAll());
    }

    /**
     * 根据id查询出库单
     */
    @GetMapping("/{id}")
    public Result<OutboundVO> getById(@PathVariable Long id) {
        return Result.success(outboundService.findById(id));
    }

    /**
     * 通用查询接口 - 支持多条件查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<OutboundVO>> search(OutboundQueryDTO query) {
        return Result.success(outboundService.search(query));
    }

    /**
     * 添加出库单
     */
    @PostMapping
    public Result<Void> add(@RequestBody OutboundDTO dto) {
        outboundService.add(dto);
        return Result.success();
    }
}
