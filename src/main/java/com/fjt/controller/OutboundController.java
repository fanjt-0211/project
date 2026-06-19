package com.fjt.controller;

import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.dto.OutboundQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.OutboundVO;
import com.fjt.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    /**
     * 分页查询出库单 - 支持动态条件查询
     */
    @GetMapping
    public Result<PageBean<OutboundVO>> list(OutboundQueryDTO query) {
        return Result.success(outboundService.list(query));
    }

    /**
     * 根据id查询出库单
     */
    @GetMapping("/{id}")
    public Result<OutboundVO> getById(@PathVariable Long id) {
        return Result.success(outboundService.findById(id));
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
