package com.fjt.mapper;

import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import java.util.List;

@Mapper
public interface MaterialMapper {
    /**
     * 添加物料
     */
    void insert(Material material);
    /**
     * 修改物料
     */
    void update(Material material);

    Material findById(Long id);
    Material findByCode(String code);
    Page<Material> search(MaterialQueryDTO query);
    List<Material> findStockWarning();
    int countByCategoryId(Long categoryId);
}
