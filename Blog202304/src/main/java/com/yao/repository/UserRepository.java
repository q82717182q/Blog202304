package com.yao.repository;

import com.yao.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import java.util.*;

/*
 * @author Jack
 * @date 2023/3/31
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
