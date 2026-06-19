package com.fjt.service.impl;

import com.fjt.mapper.MaterialCategoryMapper;
import com.fjt.mapper.MaterialMapper;
import com.fjt.pojo.PageBean;
import com.fjt.pojo.dto.MaterialDTO;
import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import com.fjt.pojo.entity.MaterialCategory;
import com.fjt.pojo.vo.MaterialVO;
import com.fjt.service.MaterialService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    /**
     * 通用查询接口 - 支持多条件分页查询
     * 参数可为空，为空则查询所有
     */
    @Override
    public PageBean<MaterialVO> list(MaterialQueryDTO query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Material> list = materialMapper.search(query);
        Page<Material> pageResult = (Page<Material>) list;
        long total = pageResult.getTotal();
        List<MaterialVO> records = pageResult.getResult().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return new PageBean<>(total, records);
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
