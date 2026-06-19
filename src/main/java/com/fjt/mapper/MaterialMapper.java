package com.fjt.mapper;

import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {
    void insert(Material material);
    void update(Material material);
    void deleteById(Long id);
    Material findById(Long id);
    Material findByCode(String code);
    List<Material> search(MaterialQueryDTO query);
    List<Material> findStockWarning();
}
