package com.yao.repository;
/*
 * @author Jack
 * @date 2023/4/2
 * */

import com.yao.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
