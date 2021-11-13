package com.example.springcloudtask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class CustomTaskConfigurer extends DefaultTaskConfigurer {
    @Autowired
    public CustomTaskConfigurer(@Qualifier("secondDataSource") DataSource dataSource)  {
        super(dataSource);
    }
}
