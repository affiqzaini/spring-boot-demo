package com.group.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean("primary")
    @ConfigurationProperties(prefix = "datasource.primary")
    DataSource kasihInsanDs() {
        return DataSourceBuilder.create().build();
    }

}
