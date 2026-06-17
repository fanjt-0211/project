package com.fjt.mapper;

import com.fjt.pojo.MaterialCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialCategoryMapper {
    void insert(MaterialCategory category);
    void update(MaterialCategory category);
    void deleteById(Long id);
    MaterialCategory findById(Long id);
    List<MaterialCategory> findAll();
    List<MaterialCategory> findByParentId(Long parentId);
    List<MaterialCategory> findByName(@Param("name") String name);
}