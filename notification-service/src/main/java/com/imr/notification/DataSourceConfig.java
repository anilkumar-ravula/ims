package com.imr.notification;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    @Value("${DB_USER}")
    private String dbUSer;

    @Value("${DB_PWD}")
    private String dnPwd;

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> tenantDataSources = new HashMap<>();

        tenantDataSources.put("clienta", createDataSource("jdbc:postgresql://postgres:5432/clienta", dbUSer, dnPwd));
        tenantDataSources.put("clientb", createDataSource("jdbc:postgresql://postgres:5432/clientb", dbUSer, dnPwd));

        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource();
        routingDataSource.setTargetDataSources(tenantDataSources);
        routingDataSource.setDefaultTargetDataSource(tenantDataSources.get("clientA"));
        return routingDataSource;
    }

    private DataSource createDataSource(String url, String username, String password) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDataSourceProperties(properties);
        return new HikariDataSource(config);
    }
}