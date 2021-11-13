package com.example.springcloudtask.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class SampleCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String...args) throws Exception {
        System.out.println("Specified Task");
    }
}


