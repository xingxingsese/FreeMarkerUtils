package com.lsc.generate.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisc on 2021/12/19
 */
public class FileUtils {
    // 得到相对路径
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file)) {
            return "";
        }
        if (baseDir.getParentFile() == null) {
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        }
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    // 查询某个目录下所有的文件
    public static List<File> searchAllFile(File dir) {
        ArrayList arrayList = new ArrayList<>();
        searchFiles(dir, arrayList);
        return arrayList;
    }

    // 递归获取某个目录下所有文件
    private static void searchFiles(File dir, ArrayList<Object> collector) {
        // isDirectory() 判断当前是否是目录
        if (dir.isDirectory()) {
            File[] subFiles = dir.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                searchFiles(subFiles[i], collector);
            }
        } else {
            collector.add(dir);
        }
    }

    // 创建文件
    public static File mkdir(String dir, String file) throws IllegalAccessException {
        if (dir == null) {
            throw new IllegalAccessException("dir must be not null");
        }
        File result = new File(dir, file);
        // getParentFile() 判断它是否有上一级目录
        if (result.getParentFile() != null){
            // mkdir() 已该路径创建目录
            result.getParentFile().mkdir();
        }
        return result;
    }
}
