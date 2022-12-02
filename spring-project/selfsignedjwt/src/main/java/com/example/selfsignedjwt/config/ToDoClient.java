package com.example.selfsignedjwt.config;


import com.example.selfsignedjwt.model.ToDo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/todos")
public interface ToDoClient {
    @GetExchange("/{todoId}")
    ToDo getToDo(@PathVariable int todoId);
}
