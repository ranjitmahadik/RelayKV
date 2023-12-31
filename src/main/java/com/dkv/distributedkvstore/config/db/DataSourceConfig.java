package com.dkv.distributedkvstore.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourceConfig {
    @Autowired
    private ShardList shardList;

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource dataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Qualifier("multipleConnections")
    public List<JdbcTemplate> loadDataSources() {
        List<JdbcTemplate> jdbcTemplateList = new ArrayList<>();
        for (ShardConfig config : shardList.getShards()) {
            HikariDataSource dataSource = DataSourceBuilder
                    .create()
                    .type(HikariDataSource.class)
                    .driverClassName(config.getDriverClassName())
                    .url(config.getJdbcUrl())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .build();
            dataSource.setMaximumPoolSize(config.getPoolSize());
//            DataSourceProperties dataSourceProperties = new DataSourceProperties();
//            dataSourceProperties.setDriverClassName(config.getDriverClassName());
//            dataSourceProperties.setName(config.getUsername());
//            dataSourceProperties.setPassword(config.getPassword());
//            dataSourceProperties.setUrl(config.getJdbcUrl());
//            dataSourceProperties.initializeDataSourceBuilder().build()
            jdbcTemplateList.add(new JdbcTemplate(dataSource));
        }
        return jdbcTemplateList;
    }
}
