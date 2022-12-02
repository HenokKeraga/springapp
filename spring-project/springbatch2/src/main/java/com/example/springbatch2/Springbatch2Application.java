package com.example.springbatch2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.sql.init.dependency.DatabaseInitializationDependencyConfigurer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "batchDataSource" ,transactionManagerRef = "batchTransactionManager")
public class Springbatch2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbatch2Application.class, args);
    }

}
