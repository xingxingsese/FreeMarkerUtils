package com.lsc.generate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lisc on 2021/12/19
 */
public class PropertiesUtils {
    public static Map<String, String> customMap = new HashMap<>();

    static {
        File dir = new File("properties");
        List<File> files = FileUtils.searchAllFile(new File(dir.getAbsolutePath()));
        for (File file : files) {
            if (file.getName().endsWith(".properties")) {
                Properties prope = new Properties();
                try {
                    prope.load(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                customMap.putAll((Map) prope);

            }
        }

    }
}
