package com.fjt.service;

import com.fjt.pojo.MaterialCategory;

import java.util.List;

public interface MaterialCategoryService {
    void add(MaterialCategory category);
    void update(MaterialCategory category);
    void delete(Long id);
    MaterialCategory findById(Long id);
    List<MaterialCategory> findAll();
    List<MaterialCategory> findByParentId(Long parentId);
    List<MaterialCategory> findByName(String name);
}