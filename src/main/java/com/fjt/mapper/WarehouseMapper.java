package com.fjt.mapper;

import com.fjt.pojo.dto.WarehouseQueryDTO;
import com.fjt.pojo.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    void insert(Warehouse warehouse);
    void update(Warehouse warehouse);
    void deleteById(Long id);
    Warehouse findById(Long id);
    Warehouse findByCode(String code);
    List<Warehouse> findAll();
    List<Warehouse> search(WarehouseQueryDTO query);
}
