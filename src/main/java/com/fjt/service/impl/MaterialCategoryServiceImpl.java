package com.fjt.service.impl;

import com.fjt.mapper.MaterialCategoryMapper;
import com.fjt.pojo.MaterialCategory;
import com.fjt.service.MaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {

    @Autowired
    private MaterialCategoryMapper materialCategoryMapper;

    @Override
    public void add(MaterialCategory category) {
        materialCategoryMapper.insert(category);
    }

    @Override
    public void update(MaterialCategory category) {
        materialCategoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        materialCategoryMapper.deleteById(id);
    }

    @Override
    public MaterialCategory findById(Long id) {
        return materialCategoryMapper.findById(id);
    }

    @Override
    public List<MaterialCategory> findAll() {
        return materialCategoryMapper.findAll();
    }

    @Override
    public List<MaterialCategory> findByParentId(Long parentId) {
        return materialCategoryMapper.findByParentId(parentId);
    }

    @Override
    public List<MaterialCategory> findByName(String name) {
        return materialCategoryMapper.findByName(name);
    }
}