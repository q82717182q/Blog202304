package com.yao.controller;

import com.yao.entity.*;
import com.yao.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/*
 * @author Jack
 * @date 2023/4/1
 * */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            String token = loginService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(token);
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.badRequest().body("login failed");
        }
    }


}
