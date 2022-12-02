package com.example.selfsignedjwt;

import com.example.selfsignedjwt.model.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SelfsignedjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfsignedjwtApplication.class, args);
    }

}

