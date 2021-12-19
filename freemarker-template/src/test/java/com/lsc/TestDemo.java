package com.lsc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsc.generate.entity.Bean;
import com.lsc.generate.entity.Column;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lisc on 2021/12/19
 */
public class TestDemo {
    @Test
    public void test() {
        Bean bean = new Bean();
        bean.setClassName("User");
        Column column = new Column();
        column.setColumnName("id");
        column.setColumnType("int");

        Column column2 = new Column();
        column2.setColumnName("name");
        column2.setColumnType("String");

        ArrayList<Column> list = new ArrayList<>();
        list.add(column);
        list.add(column2);
        bean.setColumns(list);
        System.out.println(JSON.toJSON(bean));
    }

    @Test
    public void test01() throws IOException {
        File file = new File("templateData/Bean.txt");
        if (!file.exists()) {
            System.out.println("不为空");
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));


    }

    @Test
    public void test03() throws Exception {
        Object temp = null;
        File file = new File("E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\src\\main\\resources\\templateData\\Bean2.txt");
        FileReader fileReader = new FileReader(file);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object o = ois.readObject();
        System.out.println(o );
        ois.close();
        fis.close();


    }

    @Test
    public void readJsonFile() {
        File file = new File("E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\src\\main\\resources\\templateData\\Bean.txt");
        String templatePath = "com.lsc.bean\\${className}.java";
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        String replace = absolutePath.replace(templatePath, "");
        System.out.println(replace);
    }
}

