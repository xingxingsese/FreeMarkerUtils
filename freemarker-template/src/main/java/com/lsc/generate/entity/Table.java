package com.lsc.generate.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by lisc on 2021/12/19
 */

@Data
public class Table {
    // 表名
    private String name;
    // 处理后的表名
    private String name2;
    // 描述
    private String comment;
    // 主键列
    private String key;
    // 列集合
    private List<Column> columns;
}
