package com.fjt.service;

import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.vo.MaterialVO;

import java.util.List;

public interface MaterialService {
    void add(MaterialDTO dto);
    void update(MaterialDTO dto, Long id);
    void delete(Long id);
    MaterialVO findById(Long id);
    Material findByCode(String code);
    List<MaterialVO> findAll();
    List<MaterialVO> findByCategoryId(Long categoryId);
    List<MaterialVO> search(String keyword);
    List<MaterialVO> findStockWarning();
}