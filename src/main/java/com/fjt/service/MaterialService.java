package com.fjt.service;

import com.fjt.pojo.Material;

import java.util.List;

public interface MaterialService {
    void add(Material material);
    void update(Material material);
    void delete(Long id);
    Material findById(Long id);
    Material findByCode(String code);
    List<Material> findAll();
    List<Material> findByCategoryId(Long categoryId);
    List<Material> search(String keyword);
    List<Material> findStockWarning();
}