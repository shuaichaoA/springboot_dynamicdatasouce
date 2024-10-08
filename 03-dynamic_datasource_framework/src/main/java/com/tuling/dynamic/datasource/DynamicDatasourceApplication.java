package com.tuling.dynamic.datasource;


import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.service.DsService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.tuling.dynamic.datasource.mapper")
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }
    @Bean
    public DynamicDataSourceProvider jdbcDynamicDataSourceProvider() {
        return new AbstractJdbcDataSourceProvider("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/datasource1?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8&useSSL=false", "root", "root") {
            @Override
            protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery("select * from data_source");
                Map<String, DataSourceProperty> map = new HashMap<>();
                while (rs.next()) {
                    String name = rs.getString("pool_name");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String url = rs.getString("url");
                    String driver = rs.getString("driver_class_name");
                    DataSourceProperty property = new DataSourceProperty();
                    property.setUsername(username);
                    property.setPassword(password);
                    property.setUrl(url);
                    property.setDriverClassName(driver);
                    map.put(name, property);
                }
                return map;
            }
        };
    }
}

