package com.fjt.pojo.dto;

import lombok.Data;

/**
 * 分页查询基类
 */
@Data
public class PageQueryDTO {
    /**
     * 页码，默认1
     */
    private int pageNum = 1;

    /**
     * 每页条数，默认10
     */
    private int pageSize = 10;
}
