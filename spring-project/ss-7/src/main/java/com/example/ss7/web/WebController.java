package com.example.ss7.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/private")
    public String getDemo() {
        System.out.println("demo");
        return "demo";
    }
}
