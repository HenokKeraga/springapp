package com.example.springbatch2.web;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

    final JobLauncher jobLauncher;

    final Job sampleJob;

    public JobLauncherController(JobLauncher jobLauncher, Job sampleJob) {
        this.jobLauncher = jobLauncher;
        this.sampleJob = sampleJob;
    }

    @GetMapping("/start")
    public void handle() throws Exception{
        jobLauncher.run(sampleJob, new JobParameters());
    }
}
