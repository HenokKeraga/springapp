package com.example.sc7.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/public")
    public String publicString() {
        return "public";
    }

    @GetMapping("/private")
    public String privateString(Authentication authentication) {
        return "private  " + authentication.getName();
    }
}
