package com.example.springcloudtask.config;

import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

public class AppTaskListener {
    @BeforeTask
    public void beforeTaskInvocation(TaskExecution taskExecution) {
        System.out.println("Before task");
    }

    @AfterTask
    public void afterTaskInvocation(TaskExecution taskExecution) {
        System.out.println("After task");
    }

    @FailedTask
    public void afterFailedTaskInvocation(TaskExecution taskExecution, Throwable throwable) {
        System.out.println("Failed task");
    }
}
