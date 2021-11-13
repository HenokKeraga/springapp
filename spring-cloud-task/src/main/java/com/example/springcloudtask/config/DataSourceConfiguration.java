package com.example.springcloudtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean
    public DataSource secondDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public CustomTaskConfigurer customTaskConfigurer() {
        return new CustomTaskConfigurer(secondDataSource());
    }

    @Bean
    public SampleCommandLineRunner sampleCommandLineRunner() {
        return new SampleCommandLineRunner();
    }

    @Bean
    public AppTaskListener appTaskListener() {
        return new AppTaskListener();
    }

}
