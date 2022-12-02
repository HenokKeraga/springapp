package com.example.springbatch5.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration

public class BatchConfig extends DefaultBatchConfiguration {
    //    final HikariDataSource hikariDataSource;
//
//    public BatchConfig(HikariDataSource hikariDataSource) {
//        this.hikariDataSource = hikariDataSource;
//    }
//
//    @Override
//    protected DataSource getDataSource() {
//        return hikariDataSource;
//    }
//    @Autowired
//    JobRepository jobRepository;
//    @Autowired
//    PlatformTransactionManager platformTransactionManager;
    private static int count = 0;

    @Bean
    public Job sampleJob() {

        return new JobBuilder("sample", jobRepository())
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository())
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(count + ". Hello, World!");

                    count++;

                    if (count < 5) {
                        return RepeatStatus.CONTINUABLE;
                    } else {
                        return RepeatStatus.FINISHED;
                    }


                }, getTransactionManager()).build();
    }

}
