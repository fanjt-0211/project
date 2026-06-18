package com.fjt.service;

import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
import com.fjt.pojo.entity.MaterialCategory;
import com.fjt.pojo.vo.MaterialCategoryVO;

import java.util.List;

public interface MaterialCategoryService {
    void add(MaterialCategoryDTO dto);
    void update(MaterialCategoryDTO dto, Long id);
    void delete(Long id);
    MaterialCategoryVO findById(Long id);
    List<MaterialCategoryVO> findAll();
    List<MaterialCategoryVO> search(MaterialCategoryQueryDTO query);
}
