package com.fjt.service;

import com.fjt.pojo.dto.WarehouseDTO;
import com.fjt.pojo.dto.WarehouseQueryDTO;
import com.fjt.pojo.entity.Warehouse;
import com.fjt.pojo.vo.WarehouseVO;

import java.util.List;

public interface WarehouseService {
    void add(WarehouseDTO dto);
    void update(WarehouseDTO dto, Long id);
    void delete(Long id);
    WarehouseVO findById(Long id);
    Warehouse findByCode(String code);
    List<WarehouseVO> search(WarehouseQueryDTO query);
}
