package com.lsc.generate.core;

import com.alibaba.fastjson.JSONObject;
import com.lsc.generate.utils.FileUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by lisc on 2021/12/19
 * 代码生成核心处理类
 * 是用Freemarker 完成文件生成
 * 数据模型 + 模版
 * 数据:
 * 数据模型
 * 模版位置
 * 生成文件的路径
 */

@Data
public class Generator {
    // 模版路径
    private String templatePath;
    // 代码生成路径
    private String outPath;
    // FreeMarker 连接对象
    private Configuration cfg;

    public Generator(String templatePath, String outPath) {
        this.templatePath = templatePath;
        this.outPath = outPath;
        // 实例化FreeMarker
        this.cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        try {
            // 指定模版加载器
            FileTemplateLoader ftl = new FileTemplateLoader(new File(templatePath));
            cfg.setTemplateLoader(ftl);
        } catch (IOException e) {
            System.out.println("获取模版路径出错");
        }
    }

    public static void main(String[] args) throws Exception {
        // 模版路径
        String templatePath = "E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\FreeMarkerFile\\templates";
        // 生成类路径
        String outPath = "E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\FreeMarkerFile\\outtemplates";

        Generator generator = new Generator(templatePath, outPath);
        // 获取元数据
        Map<String, Object> dataModel = getDataModel("E:\\TestCodeDome\\FreeMarkerUtils\\freemarker-template\\src\\main\\resources\\templateData\\Bean.txt");
        dataModel.put("package","com.lsc.bean");

        generator.scanAndGenerator(dataModel);
    }

    /**
     * 返回数据模型
     *
     * @param jsonPath
     * @return
     */
    public static Map<String, Object> getDataModel(String jsonPath) {
        String jsonStr = "";
        try {
            File jsonFile = new File(jsonPath);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return JSONObject.parseObject(jsonStr, Map.class);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 代码生成
     * 1 扫描模版路径下所有模版
     * 2 对每个模版进行文件生成
     *
     * @param dataModel 数据模型
     */
    public void scanAndGenerator(Map<String, Object> dataModel) throws Exception {
        //1  根据模版路径找到此路径下所有模版文件
        List<File> fileList = FileUtils.searchAllFile(new File(templatePath));
        for (File file : fileList) {
            executeGenertor(dataModel, file);
        }
    }

    /**
     * 对模版进行代码生成
     *
     * @param dataModel 数据模型
     * @param file      模版文件
     */
    private void executeGenertor(Map<String, Object> dataModel, File file) throws Exception {
        // 1 文件路径处理 getAbsolutePath() 获取到当前file的绝对路径
        // E:\TestCodeDome\FreeMarkerUtils\freemarker-template\templates\outtemplates\
        //      ${path1}\${path2}\${path3}\${className}.java
        // 把前面的路径替换成空,只处理后面的路径
        String templateFileName = file.getAbsolutePath().replace(this.templatePath, "");
        String stringTemplate = processStringTemplate(templateFileName, dataModel);

        // 2 读取文件模版
        Template template = cfg.getTemplate(templateFileName);
        // 指定生成文件的字符集编码
        template.setOutputEncoding("utf-8");

        // 3 创建文件
        File file1 = FileUtils.mkdir(outPath, stringTemplate);

        // 4 文件生成
        FileWriter fw = new FileWriter(file1);
        template.process(dataModel, fw);
        fw.close();
    }

    public String processStringTemplate(String templateString, Map<String, Object> dataModel) throws Exception {
        StringWriter out = new StringWriter();
        /**
         *  创建字符串模版
         */
        Template template = new Template("StringTemplate", new StringReader(templateString), cfg);
        // 输出到out
        template.process(dataModel, out);
        return out.toString();
    }
}
