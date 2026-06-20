package com.fjt.service.impl;

import com.fjt.mapper.MaterialCategoryMapper;
import com.fjt.mapper.MaterialMapper;
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

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 添加分类
     */
    @Override
    public void add(MaterialCategoryDTO dto) {
        MaterialCategory category = new MaterialCategory();
        BeanUtils.copyProperties(dto, category);
        category.setStatus(1);
        materialCategoryMapper.insert(category);
    }

    /**
     * 修改分类
     */
    @Override
    public void update(MaterialCategoryDTO dto, Long id) {
        MaterialCategory category = new MaterialCategory();
        BeanUtils.copyProperties(dto, category);
        category.setId(id);
        materialCategoryMapper.update(category);
    }

    /**
     * 更新状态（启用/禁用）
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        if (status == 0) {
            int count = materialMapper.countByCategoryId(id);
            if (count > 0) {
                throw new RuntimeException("该分类下还有物资，无法禁用");
            }
        }
        MaterialCategory category = new MaterialCategory();
        category.setId(id);
        category.setStatus(status);
        materialCategoryMapper.update(category);
    }

    /**
     * 根据id查询分类
     */
    @Override
    public MaterialCategoryVO findById(Long id) {
        MaterialCategory category = materialCategoryMapper.findById(id);
        return category != null ? convertToVO(category) : null;
    }

    /**
     * 查询所有分类 - 支持多条件查询
     */
    @Override
    public List<MaterialCategoryVO> search(MaterialCategoryQueryDTO query) {
        return materialCategoryMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    /**
     * 转换为VO
     */
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
