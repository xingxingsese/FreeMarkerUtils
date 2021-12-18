package com.lsc.metadata.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lisc on 2021/12/19
 * 测试结果集元数据
 *      通过ResultSet获取
 *      获取查询结果的信息
 */
public class ResultSetMetaDataTest {
    private Connection connection;

    /**
     * @Before – 表示在任意使用@Test注解标注的public void方法执行之前执行
     */
    @Before
    public void init() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/guli_admin";
        String username = "root";
        String password = "root";

        Properties properties = new Properties();
        // 获取数据库的备注信息
        properties.put("remarksReporting", "true");
        properties.put("user", username);
        properties.put("password", password);

        // 1 获取连接
        // 注册驱动
        Class.forName(driver);
        connection = DriverManager.getConnection(url, properties);
    }

    /**
     *  表示在任意使用@Test注解标注的public void方法执行之后执行
     */
    @After
    public void after(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws SQLException {
        String sql = "select * from tb_user where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        // 给第一个?占位符赋值
        ps.setString(1,"1");
        // 查询
        ResultSet resultSet = ps.executeQuery();
        // 获取查询结果集数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 获取查询字段个数
        int count = metaData.getColumnCount();
        for (int i = 1; i < count; i++) {
            System.out.println("列名: " + metaData.getColumnName(i));
           // System.out.println("类型: " + metaData.getColumnType(i));
            System.out.println("数据库字段类型: " + metaData.getColumnTypeName(i));
            System.out.println("Java数据类型: " + metaData.getColumnClassName(i));
            System.out.println("ColumnLabel: " + metaData.getColumnLabel(i));
            System.out.println("数据库名称: " + metaData.getCatalogName(i));
            System.out.println("ColumnDisplaySize: " + metaData.getColumnDisplaySize(i));
        }
        //System.out.println(metaData);

        resultSet.close();
        ps.close();
    }
}
