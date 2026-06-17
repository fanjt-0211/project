package com.fjt.service.impl;

import com.fjt.mapper.MaterialMapper;
import com.fjt.pojo.Material;
import com.fjt.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public void add(Material material) {
        materialMapper.insert(material);
    }

    @Override
    public void update(Material material) {
        materialMapper.update(material);
    }

    @Override
    public void delete(Long id) {
        materialMapper.deleteById(id);
    }

    @Override
    public Material findById(Long id) {
        return materialMapper.findById(id);
    }

    @Override
    public Material findByCode(String code) {
        return materialMapper.findByCode(code);
    }

    @Override
    public List<Material> findAll() {
        return materialMapper.findAll();
    }

    @Override
    public List<Material> findByCategoryId(Long categoryId) {
        return materialMapper.findByCategoryId(categoryId);
    }

    @Override
    public List<Material> search(String keyword) {
        return materialMapper.search(keyword);
    }

    @Override
    public List<Material> findStockWarning() {
        return materialMapper.findStockWarning();
    }
}