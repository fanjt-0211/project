package com.fjt.mapper;

import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
import com.fjt.pojo.entity.MaterialCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialCategoryMapper {
    void insert(MaterialCategory category);
    void update(MaterialCategory category);
    void deleteById(Long id);
    MaterialCategory findById(Long id);
    List<MaterialCategory> search(MaterialCategoryQueryDTO query);
}
