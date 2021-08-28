package com.example.persistanceone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

  @Bean
  @ConfigurationProperties(prefix = "first.datasource")
  public DataSource firstDatasource() {
    return DataSourceBuilder.create().build();
  }
}
