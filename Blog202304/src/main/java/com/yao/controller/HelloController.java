package com.yao.controller;

import com.yao.entity.*;
import com.yao.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/*
 * @author Jack
 * @date 2023/3/30
 * */
@Slf4j
@RestController
@RequestMapping("/public")
public class HelloController {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }
    @GetMapping("/test")
    public ResponseEntity<?> sayHelloTest(){
        return ResponseEntity.status(HttpStatus.OK).body("hello test");
    }
    @GetMapping("/home")
    public ResponseEntity<?> home(){
        return ResponseEntity.status(HttpStatus.OK).body("home test");
    }
}
