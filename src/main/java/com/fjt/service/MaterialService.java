package com.fjt.service;

import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.vo.MaterialVO;

import com.fjt.pojo.PageBean;
import java.util.List;

public interface MaterialService {
    void add(MaterialDTO dto);
    void update(MaterialDTO dto, Long id);
    MaterialVO findById(Long id);
    Material findByCode(String code);
    PageBean<MaterialVO> list(MaterialQueryDTO query);
    List<MaterialVO> findStockWarning();
}
