package com.example.selfsignedjwt.web;

import com.example.selfsignedjwt.config.ToDoClient;
import com.example.selfsignedjwt.service.TokenService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
public class HelloController {
    final ObservationRegistry registry;
    final ToDoClient toDoClient;
    final TokenService tokenService;

    public HelloController(ObservationRegistry registry, ToDoClient toDoClient, TokenService tokenService) {
        this.registry = registry;
        this.toDoClient = toDoClient;
        this.tokenService = tokenService;
    }


    @PostMapping("/token")
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }


    @GetMapping("/hello")
    public String hello() {
//        var newContext = SecurityContextHolder.createEmptyContext();
//        newContext.setAuthentication(new UsernamePasswordAuthenticationToken("hen", "12345"));
//
//        SecurityContextHolder.setContext(newContext);

        var authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication.getName().equals("hen")) {
//            throw new IllegalArgumentException(" illegal");
//        }

        var toDo = toDoClient.getToDo(2);

        return Observation
                .createNotStarted("greeting ", this.registry)
                .observe(() -> new Greeting("Hello ") + authentication.getName() +"  "+ toDo);
    }
}

@ControllerAdvice
class HelloErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    // @ResponseBody
    public ProblemDetail onError() {
        var problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "auathorize");

        problemDetail.setProperty("Error_code ", "fasdffadsfjk");

        return problemDetail;

    }
}

record Greeting(String name) {
};



