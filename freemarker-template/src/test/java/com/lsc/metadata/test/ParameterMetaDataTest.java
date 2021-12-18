package com.lsc.metadata.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lisc on 2021/12/19
 * 测试参数元数据
 * PreparedStatement
 * 获取sql参数中的属性信息
 * 用的比较少
 */
public class ParameterMetaDataTest {
    private Connection connection;

    /**
     * @Before – 表示在任意使用@Test注解标注的public void方法执行之前执行
     */
    @Before
    public void init() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306";
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
     * 表示在任意使用@Test注解标注的public void方法执行之后执行
     */
    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01() throws SQLException {
        String sql = "select * from tb_user where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1,"0001");

        // 获取参数元数据
        ParameterMetaData metaData = ps.getParameterMetaData();
        // 获取参数个数
        int parameterCount = metaData.getParameterCount();
        System.out.println(parameterCount);
        ps.close();

    }
}
