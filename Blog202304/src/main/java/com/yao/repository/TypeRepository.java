package com.yao.repository;
/*
 * @author Jack
 * @date 2023/4/2
 * */

import com.yao.entity.Blog;
import com.yao.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
  Type findByName(String name);
}
