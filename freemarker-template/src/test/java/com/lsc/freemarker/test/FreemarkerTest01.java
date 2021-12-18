package com.lsc.freemarker.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lisc on 2021/12/18
 * 第一个freeMarker程序 (数据+模版=文件输出)
 *      文件模版
 */
public class FreemarkerTest01 {

    /**
     * 通过文件创建模版
     * @throws Exception
     */
    @Test
    public void testFileTemplateLoader() throws Exception {
        // 1 创建FreeMarker的配置类
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        // 2 制定模版加载器: 将模版存入缓存中
        // ClassTemplateLoader() 类路径
        // FileTemplateLoader() 文件路径加载器
        // StringTemplateLoader() 文本
        // URLTemplateLoader() url路径
        // WebappTemplateLoader web应用程序下
        FileTemplateLoader ftl = new FileTemplateLoader(new File("templates"));
        // 设置模版
        cfg.setTemplateLoader(ftl);

        // 3 获取模版
        Template template = cfg.getTemplate("template01.ftl");
        // 4 构造数据模型  map的key就是模版内占位符的key
        HashMap<String, Object> dateModel = new HashMap<>();
        dateModel.put("username","何以解忧");
        // 测试if指令
        dateModel.put("flag",1);
        // 测试list指令
        ArrayList<String> list = new ArrayList<>();
        list.add("星期一");
        list.add("星期二");
        list.add("星期三");
        list.add("星期四");
        list.add("星期五");
        dateModel.put("weeks",list);

        // 5 文件输出
        /**
         * 文件输出 也叫处理模型
         *      参数一: 数据模型
         *      参数二: writer(FileWriter(文件输出), printWriter(控制台输出) )
         */
        // 指定文件输出位置
        /*template.process(dateModel,new FileWriter(
                // 文件输出的位置
                new File("E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\templates\\outtemplates\\a.txt")));
*/
        // 指定输出到控制台
        template.process(dateModel,new PrintWriter(System.out));

    }

    /**
     * 通过字符串创建模版
     * @throws Exception
     */
    @Test
    public void testStringReaderTemplateLoader() throws Exception {
        // 1 创建FreeMarker的配置类
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        // 2 指定模版加载器
        cfg.setTemplateLoader(new StringTemplateLoader());
        /**
         * 3 创建字符串模版
         *     i.字符串
         *     ii.通过字符串创建模版
         *
         */
        String templateString = "欢迎您:${username}";
        Template template = new Template("模版名称",new StringReader(templateString),cfg);
        // 4 构造数据模型/
        HashMap<String, Object> dateModel = new HashMap<>();
        dateModel.put("username","何以解忧");

        // 5 文件输出
        // 输出到字符串
       /* StringWriter stringWriter = new StringWriter();
        template.process(dateModel,stringWriter);*/

        // 输出到控制台
        template.process(dateModel,new PrintWriter(System.out));
    }
}
