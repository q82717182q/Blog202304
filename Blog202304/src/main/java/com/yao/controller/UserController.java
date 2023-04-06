package com.yao.controller;

import com.yao.entity.*;
import com.yao.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/*
 * @author Jack
 * @date 2023/3/31
 * */
@RestController
@Slf4j
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String register(){
        return "register";
    }

    @PostMapping
    public User register(@RequestBody User user){
        try{
            userService.register(user);
        }catch (Exception e){
            log.error("",e);
        }
        return user;
    }


}
