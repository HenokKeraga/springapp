package com.example.springcloudtask.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Configuration
public class JobConfiguration {
    private final static Logger LOGGER = Logger
            .getLogger(JobConfiguration.class.getName());

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("job1step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(
                            StepContribution contribution,
                            ChunkContext chunkContext)
                            throws Exception {
                        LOGGER.info("Tasklet has run");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory
                .get("job1step2")
                .<String, String>chunk(3)
                .reader(
                        new ListItemReader<>(Arrays.asList("7",
                                "2", "3", "10", "5", "6")))
                .processor(
                        new ItemProcessor<String, String>() {
                            @Override
                            public String process(String item)
                                    throws Exception {
                                LOGGER.info("Processing of chunks");
                                return String.valueOf(Integer
                                        .parseInt(item) * -1);
                            }
                        })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(
                            List<? extends String> items)
                            throws Exception {
                        for (String item : items) {
                            LOGGER.info(">> " + item);
                        }
                    }
                }).build();
    }

    @Bean
    public Job job1() {
        return this.jobBuilderFactory.get("job1")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        LOGGER.info("before job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        LOGGER.info("After job");
                        if (jobExecution.getExitStatus().equals(ExitStatus.FAILED)){
                            LOGGER.info("After job "+"FAILED");
                        }

                    }
                })
                .start(stepBuilderFactory.get("job2step1")
                        .listener(new StepExecutionListener() {
                            @Override
                            public void beforeStep(StepExecution stepExecution) {
                                LOGGER.info("before step");
                            }

                            @Override
                            public ExitStatus afterStep(StepExecution stepExecution) {
                                LOGGER.info("After step");
                                if (stepExecution.getExitStatus().equals(ExitStatus.FAILED)){
                                    LOGGER.info("After step " + "FAILED" );
                                    return ExitStatus.FAILED;
                                }
                                return ExitStatus.COMPLETED;
                            }
                        })
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(
                                    StepContribution contribution,
                                    ChunkContext chunkContext)
                                    throws Exception {
                                LOGGER.info("This job is from Baeldung");
                                contribution.setExitStatus(ExitStatus.FAILED);
                                return RepeatStatus.FINISHED;
                            }
                        })
                        .build())
                .build();
    }
}
