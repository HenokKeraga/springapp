package com.example.springbatch5;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "datasource",transactionManagerRef = "transactionManager")
public class Springbatch5Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbatch5Application.class, args);
    }

}
