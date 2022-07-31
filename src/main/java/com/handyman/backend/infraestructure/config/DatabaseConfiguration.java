package com.handyman.backend.infraestructure.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
@Configuration

public class DatabaseConfiguration {


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/handyman");
        builder.username("root");
        builder.password("db123456");
        System.out.println("My custom datasource has been initialized");
        return builder.build();
    }

}
