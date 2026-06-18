package com.fjt.mapper;

import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.entity.Inbound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InboundMapper {
    void insert(Inbound inbound);
    void update(Inbound inbound);
    Inbound findById(Long id);
    Inbound findByInboundNo(String inboundNo);
    List<Inbound> findAll();
    List<Inbound> search(InboundQueryDTO query);
}
