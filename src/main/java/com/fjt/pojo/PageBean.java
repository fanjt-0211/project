package com.fjt.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    private Integer total;
    private List<T> records;

    public PageBean() {}

    public PageBean(Integer total, List<T> records) {
        this.total = total;
        this.records = records;
    }
}