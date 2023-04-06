package com.yao.repository;
/*
 * @author Jack
 * @date 2023/4/2
 * */

import com.yao.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
  Tag findByName(String name);
}
