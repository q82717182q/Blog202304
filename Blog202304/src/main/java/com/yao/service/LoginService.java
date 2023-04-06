package com.yao.service;

import com.yao.entity.*;
import com.yao.repository.*;
import com.yao.util.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

/*
 * @author Jack
 * @date 2023/4/1
 * */
@Slf4j
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    public String login(String email, String password){
        User user = userRepository.findByEmail(email);
        boolean isPasswordMatched = new BCryptPasswordEncoder().matches(password, user.getPassword());
        if (user == null || !isPasswordMatched){
            throw new RuntimeException("用戶名或密碼錯誤");
        }
        return JwtUtils.generateToken(user.getEmail());
    }
}
