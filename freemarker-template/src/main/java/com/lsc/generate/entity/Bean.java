package com.lsc.generate.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by lisc on 2021/12/19
 */
@Data
public class Bean {
    // 类名
    private String className;
    // 属性类型和属性名
    private List<Column> columns;
}
