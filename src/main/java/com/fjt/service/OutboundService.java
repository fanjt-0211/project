package com.fjt.service;

import com.fjt.pojo.dto.OutboundDTO;
import com.fjt.pojo.dto.OutboundQueryDTO;
import com.fjt.pojo.entity.Outbound;
import com.fjt.pojo.vo.OutboundVO;

import com.fjt.pojo.PageBean;

import java.util.List;

public interface OutboundService {
    void add(OutboundDTO dto);
    void update(Outbound outbound);
    OutboundVO findById(Long id);
    OutboundVO findByOutboundNo(String outboundNo);
    PageBean<OutboundVO> list(OutboundQueryDTO query);
}
