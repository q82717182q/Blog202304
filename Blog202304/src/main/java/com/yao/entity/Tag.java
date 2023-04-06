package com.yao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@Entity
@Table(name = "tag")
@Data
public class Tag extends AbstractBaseEntity {
  private String name;
  @ManyToMany(mappedBy = "tags")
  private List<Blog> blogs = new ArrayList<>();
}


/*

CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,

  `name` varchar(255) DEFAULT NULL,
   `create_time` timestamp NULL DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` int DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


 */