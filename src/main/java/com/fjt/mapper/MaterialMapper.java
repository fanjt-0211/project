package com.fjt.mapper;

import com.fjt.pojo.dto.MaterialQueryDTO;
import com.fjt.pojo.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 根据id查询物料
     */
    Material findById(Long id);
    /**
     * 根据编码查询物料
     */
    Material findByCode(String code);
    /**
     * 通用查询接口 - 支持多条件分页查询
     * <p>参数可为空，为空则查询所有</p>
     */
    List<Material> search(MaterialQueryDTO query);
    /**
     * 根据分类id查询物料数量
     */
    int countByCategoryId(Long categoryId);
}
