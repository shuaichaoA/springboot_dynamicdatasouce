package com.tuling.dynamic.datasource.entity;

import lombok.Data;

import javax.sql.DataSource;

/**
 *
 * @Description:
 */
@Data
public class Ds {
    /**
     * 连接池名称(只是一个名称标识)</br> 默认是配置文件上的名称
     */
    private String pool_name;
    /**
     * 连接池类型，如果不设置自动查找 Druid > HikariCp
     */
    private String type;
    /**
     * JDBC driver
     */
    private String driver_class_name;
    /**
     * JDBC url 地址
     */
    private String url;
    /**
     * JDBC 用户名
     */
    private String username;
    /**
     * JDBC 密码
     */
    private String password;
}