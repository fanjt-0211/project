package com.fjt.service.impl;

import com.fjt.mapper.MaterialCategoryMapper;
import com.fjt.mapper.MaterialMapper;
import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.entity.MaterialCategory;
import com.fjt.pojo.vo.MaterialVO;
import com.fjt.service.MaterialService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private MaterialCategoryMapper categoryMapper;

    @Override
    public void add(MaterialDTO dto) {
        Material material = new Material();
        BeanUtils.copyProperties(dto, material);
        material.setStatus(1);
        materialMapper.insert(material);
    }

    @Override
    public void update(MaterialDTO dto, Long id) {
        Material material = new Material();
        BeanUtils.copyProperties(dto, material);
        material.setId(id);
        materialMapper.update(material);
    }

    @Override
    public void delete(Long id) {
        materialMapper.deleteById(id);
    }

    @Override
    public MaterialVO findById(Long id) {
        Material material = materialMapper.findById(id);
        return material != null ? convertToVO(material) : null;
    }

    @Override
    public Material findByCode(String code) {
        return materialMapper.findByCode(code);
    }

    @Override
    public List<MaterialVO> findAll() {
        return materialMapper.findAll().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialVO> search(MaterialQueryDTO query) {
        return materialMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialVO> findStockWarning() {
        return materialMapper.findStockWarning().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private MaterialVO convertToVO(Material material) {
        MaterialVO vo = new MaterialVO();
        BeanUtils.copyProperties(material, vo);
        if (material.getCategoryId() != null) {
            MaterialCategory category = categoryMapper.findById(material.getCategoryId());
            if (category != null) {
                vo.setCategoryName(category.getName());
            }
        }
        return vo;
    }
}
