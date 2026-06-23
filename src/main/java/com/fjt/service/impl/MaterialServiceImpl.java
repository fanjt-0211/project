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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 添加物料
     */
    @Override
    public void add(MaterialDTO dto) {
        // 校验物资编码是否重复
        if (materialMapper.findByCode(dto.getCode()) != null) {
            throw new RuntimeException("物资编码已存在");
        }
        // 校验分类是否存在且启用
        MaterialCategory category = categoryMapper.findById(dto.getCategoryId());
        if (category == null) {
            throw new RuntimeException("所选分类不存在或已禁用");
        }
        Material material = new Material();
        BeanUtils.copyProperties(dto, material);
        materialMapper.insert(material);
    }

    /**
     * 修改物料
     */
    @Override
    public void update(MaterialDTO dto, Long id) {
        // 校验物资编码是否与其他记录重复
        Material existing = materialMapper.findByCode(dto.getCode());
        if (existing != null && !existing.getId().equals(id)) {
            throw new RuntimeException("物资编码已存在");
        }
        Material material = new Material();
        BeanUtils.copyProperties(dto, material);
        material.setId(id);
        materialMapper.update(material);
    }

    /**
     * 根据id查询物料
     */
    @Override
    public MaterialVO findById(Long id) {
        Material material = materialMapper.findById(id);
        return material != null ? convertToVO(material) : null;
    }

    /**
     * 根据编码查询物料
     */
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
        PageInfo<Material> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        List<MaterialVO> records = pageInfo.getList().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return new PageBean<>(total, records);
    }

    /**
     * 转换为VO
     */
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
