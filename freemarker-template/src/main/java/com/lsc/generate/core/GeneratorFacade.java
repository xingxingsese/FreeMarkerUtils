package com.lsc.generate.core;

import com.lsc.generate.entity.DataBase;
import com.lsc.generate.entity.Settings;
import com.lsc.generate.entity.Table;
import com.lsc.generate.utils.PropertiesUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lisc on 2021/12/19
 * 1  采集输入数据
 *      模版位置
 *      代码生成路径
 *      工程配置对象
 *      数据库对象
 *  2  准备数据模型
 *          自定义配置
 *          元数据
 *  3  调用核心处理类完成代码生成工作
 */
@Data

public class GeneratorFacade {

    // 模版路径
    private String templatePath;
    // 输出路径
    private String outPath;
    // 配置对象
    private Settings settings;
    // 数据库对象
    private DataBase db;
    private Generator generator;

    public GeneratorFacade(String templatePath, String outPath, Settings settings, DataBase db, Generator generator) {
        this.templatePath = templatePath;
        this.outPath = outPath;
        this.settings = settings;
        this.db = db;
        this.generator = new Generator(templatePath,outPath);
    }

    /**
     * 1 准备数据模型
     * 2 调用核心处理类完成代码生成工作
     */
    public void generatorByDataBase(){
        Map<String,Object> dataModel = getDataModel("");
    }

    /**
     * 获取数据模型
     * @param s
     * @return
     */
    private Map<String, Object> getDataModel(Object s) {
        Map<String,Object> dataModel =new HashMap<>();
        // 自定义配置
        dataModel.putAll(PropertiesUtils.customMap);

        // 元数据
        //dataModel.putAll("table", Table);

        dataModel.putAll(this.settings.getSettingMap());

        return dataModel;
    }
}
