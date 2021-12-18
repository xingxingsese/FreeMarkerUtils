package com.lsc.metadata.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lisc on 2021/12/18
 * 测试数据库元数据
 */
public class DataBaseMetaDataTest {

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

    // 获取数据库基本信息
    @Test
    public void test01() throws Exception {


        // 2 获取元数据
        DatabaseMetaData metaData = connection.getMetaData();

        // 3 获取数据库基本信息
        String userName = metaData.getUserName();// 数据库用户名
        String url1 = metaData.getURL();// 获取数据库连接的url 等等等等
        System.out.println("userName: " + userName + "url: " + url1);

    }

    // 获取数据库的列表
    @Test
    public void test02() throws SQLException {
        // 1 获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        // 2 获取所有数据库列表
        ResultSet rs = metaData.getCatalogs();
        while (rs.next()) {
            System.out.println("数据库名称: " + rs.getString(1));
        }
        rs.close();

    }

    /**
     * 获取数据库表的表信息
     *
     * @throws SQLException
     */
    @Test
    public void test03() throws SQLException {
        // 1 获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        // 2 获取数据库中所有表信息
        /**
         * String catalog,  #当前连接的数据库
         * String schemaPattern,    # null mysql中没什么意义  oracle中是用户名
         * String tableNamePattern,     # 要操作的表名,null代表查询所有表, 非空: 查询目标表
         * String types[]   # 类型,  TABLE:表  VIEW: 视图
         *
         */
        ResultSet tables = metaData.getTables("guli_admin", "", "", new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println("获取表名 " + tables.getString("TABLE_NAME"));
        }

        // 获取表中的字段信息
        /**
         * String catalog, 库名 , 连接中如果指定的话,这里可以不写
         * String schemaPattern, # null mysql中没什么意义  oracle中是用户名
         * String tableNamePattern, # 要操作的表名,null代表查询所有表, 非空: 查询目标表
         * String columnNamePattern # 要查询的列名,null代表查询所有列, 非空: 查询目标列
         */
        ResultSet columns = metaData.getColumns("guli_admin", "", "tb_user", null);
        while (columns.next()) {
            // 具体想获取什么可以去JDK文档中搜对应的方法getColumns() 所有相关列的 getTables() 所有相关表的
            System.out.println("获取列名: " + columns.getString("COLUMN_NAME"));
        }
        columns.close();
    }
}

