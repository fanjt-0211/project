package com.fjt.service;

import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
import com.fjt.pojo.entity.MaterialCategory;
import com.fjt.pojo.vo.MaterialCategoryVO;

public interface MaterialCategoryService {
    void add(MaterialCategoryDTO dto);
    void update(MaterialCategoryDTO dto, Long id);
    void updateStatus(Long id, Integer status);
    MaterialCategoryVO findById(Long id);
    PageBean<MaterialCategoryVO> search(MaterialCategoryQueryDTO query);
}
