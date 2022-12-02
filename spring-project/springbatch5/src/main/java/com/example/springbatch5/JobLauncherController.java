package com.example.springbatch5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class JobLauncherController {


    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job sampleJob;

    @GetMapping("/start")
    public void handle() throws Exception{
        jobLauncher.run(sampleJob, new JobParameters());
    }
}
