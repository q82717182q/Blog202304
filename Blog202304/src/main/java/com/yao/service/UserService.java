package com.yao.service;

import com.yao.entity.User;
import com.yao.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

/*
 * @author Jack
 * @date 2023/3/31
 * */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user){
            // 密码加密
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
