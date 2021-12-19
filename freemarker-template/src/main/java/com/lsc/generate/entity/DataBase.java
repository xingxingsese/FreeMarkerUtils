package com.lsc.generate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lisc on 2021/12/19
 */
@Data
@NoArgsConstructor
public class DataBase {
    private static String mysqlUrl = "jdbc:mysql://[ip]:[port]/[db]?useUnicode=true&amp;characterEncoding=UTF8";
    private static String oracleUrl = "jdbc:oracle:thin:@[ip]:[port]:[db]";
    private String dbType;
    private String driver;
    private String userName;
    private String passWord;
    private String url;

    public DataBase(String dbType){
        this(dbType,"127.0.0.1","3306","");
    }
    public DataBase(String dbType,String db){
        this(dbType,"127.0.0.1","3306",db);
    }
    public DataBase(String dbType,String ip,String port,String db){
        this.dbType = dbType;
        if ("MYSQL".endsWith(dbType.toUpperCase())){
            this.driver = "com.mysql.jdbc.Driver";
            this.url = mysqlUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }else {
            this.driver = "oracle.jdbc.driver.OracleDriver";
            this.url = mysqlUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }
    }
}
