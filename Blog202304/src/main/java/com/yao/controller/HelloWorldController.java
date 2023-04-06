package com.yao.controller;
/*
 * @author Jack
 * @date 2023/1/2
 * */

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Catherine said that I love you.";
    }

}
