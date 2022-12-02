package com.example.selfsignedjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

    @Bean
    public ToDoClient toDoClient() {

        var webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        var webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
                .clientAdapter(webClientAdapter)
                .build();

        return factory.createClient(ToDoClient.class);
    }


}



