package com.fjt.controller;

import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.InboundVO;
import com.fjt.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inbound")
public class InboundController {

    @Autowired
    private InboundService inboundService;

    /**
     * 分页查询入库单 - 支持动态条件查询
     */
    @GetMapping
    public Result<PageBean<InboundVO>> list(InboundQueryDTO query) {
        return Result.success(inboundService.list(query));
    }

    /**
     * 根据id查询入库单
     */
    @GetMapping("/{id}")
    public Result<InboundVO> getById(@PathVariable Long id) {
        return Result.success(inboundService.findById(id));
    }

    /**
     * 添加入库单
     */
    @PostMapping
    public Result<Void> add(@RequestBody InboundDTO dto) {
        inboundService.add(dto);
        return Result.success();
    }
}
