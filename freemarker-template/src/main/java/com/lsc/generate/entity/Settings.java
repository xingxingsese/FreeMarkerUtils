package com.lsc.generate.entity;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lisc on 2021/12/19
 */
@Data
public class Settings {
    private String project = "example";
    private String pPackage = "com.example.demo";
    // 项目描述信息
    private String projectComment;
    private String author;
    private String path1 = "com";
    private String path2 = "example";
    private String path3 = "demo";
    private String pathAll;

    public Settings(String project, String pPackage, String projectComment, String author) {
        this.project = project;
        this.pPackage = pPackage;
        this.projectComment = projectComment;
        this.author = author;
    }

    public Map<String,Object> getSettingMap(){
        HashMap<String, Object> map = new HashMap<>();
        Field[] declaredFields = Settings.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(),field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
