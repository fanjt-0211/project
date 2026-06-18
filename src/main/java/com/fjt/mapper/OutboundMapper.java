package com.fjt.mapper;

import com.fjt.pojo.dto.OutboundQueryDTO;
import com.fjt.pojo.entity.Outbound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutboundMapper {
    void insert(Outbound outbound);
    void update(Outbound outbound);
    Outbound findById(Long id);
    Outbound findByOutboundNo(String outboundNo);
    List<Outbound> findAll();
    List<Outbound> search(OutboundQueryDTO query);
}
