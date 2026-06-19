package com.fjt.service;

import com.fjt.pojo.dto.InboundDTO;
import com.fjt.pojo.dto.InboundQueryDTO;
import com.fjt.pojo.entity.Inbound;
import com.fjt.pojo.vo.InboundVO;

import com.fjt.pojo.PageBean;
import java.util.List;

public interface InboundService {
    void add(InboundDTO dto);
    void update(Inbound inbound);
    InboundVO findById(Long id);
    InboundVO findByInboundNo(String inboundNo);
    PageBean<InboundVO> list(InboundQueryDTO query);
}
