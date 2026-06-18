package com.fjt.service.impl;

import com.fjt.mapper.MaterialCategoryMapper;
import com.fjt.pojo.dto.MaterialCategoryDTO;
import com.fjt.pojo.dto.MaterialCategoryQueryDTO;
import com.fjt.pojo.entity.MaterialCategory;
import com.fjt.pojo.vo.MaterialCategoryVO;
import com.fjt.service.MaterialCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {

    @Autowired
    private MaterialCategoryMapper materialCategoryMapper;

    @Override
    public void add(MaterialCategoryDTO dto) {
        MaterialCategory category = new MaterialCategory();
        BeanUtils.copyProperties(dto, category);
        category.setIsDeleted(0);
        materialCategoryMapper.insert(category);
    }

    @Override
    public void update(MaterialCategoryDTO dto, Long id) {
        MaterialCategory category = new MaterialCategory();
        BeanUtils.copyProperties(dto, category);
        category.setId(id);
        materialCategoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        materialCategoryMapper.deleteById(id);
    }

    @Override
    public MaterialCategoryVO findById(Long id) {
        MaterialCategory category = materialCategoryMapper.findById(id);
        return category != null ? convertToVO(category) : null;
    }

    @Override
    public List<MaterialCategoryVO> findAll() {
        return materialCategoryMapper.findAll().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialCategoryVO> search(MaterialCategoryQueryDTO query) {
        return materialCategoryMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private MaterialCategoryVO convertToVO(MaterialCategory category) {
        MaterialCategoryVO vo = new MaterialCategoryVO();
        BeanUtils.copyProperties(category, vo);
        if (category.getParentId() != null) {
            MaterialCategory parent = materialCategoryMapper.findById(category.getParentId());
            if (parent != null) {
                vo.setParentName(parent.getName());
            }
        }
        return vo;
    }
}
