package com.fjt.controller;

import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.InboundVO;
import com.fjt.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inbound")
public class InboundController {

    @Autowired
    private InboundService inboundService;

    /**
     * 查询所有入库单
     */
    @GetMapping
    public Result<List<InboundVO>> list() {
        return Result.success(inboundService.findAll());
    }

    /**
     * 根据id查询入库单
     */
    @GetMapping("/{id}")
    public Result<InboundVO> getById(@PathVariable Long id) {
        return Result.success(inboundService.findById(id));
    }

    /**
     * 通用查询接口 - 支持多条件查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<InboundVO>> search(InboundQueryDTO query) {
        return Result.success(inboundService.search(query));
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
