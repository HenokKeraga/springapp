package com.smile.restapplication.hellloworld

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorld {
    @GetMapping()
    fun helloWorld():String =" hello "
}
