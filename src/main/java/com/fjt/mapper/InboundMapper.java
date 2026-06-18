package com.fjt.mapper;

import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.entity.Inbound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InboundMapper {
    /**
     * 添加入库单
     */
    void insert(Inbound inbound);

    /**
     * 修改入库单
     */
    void update(Inbound inbound);

    /**
     * 根据入库单id查询入库单
     */
    Inbound findById(Long id);

    /**
     * 根据入库单编号查询入库单
     */
    Inbound findByInboundNo(String inboundNo);

    /**
     * 查询所有入库单
     */
    List<Inbound> findAll();

    /**
     * 通用查询接口 - 支持多条件模糊查询
     *
     * 参数可为空，为空则查询所有
     */
    List<Inbound> search(InboundQueryDTO query);
}
