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
@Table(name = "comment")
@Data
public class Comment extends AbstractBaseEntity {
    private String title;
    @ManyToOne
    private User user;
    private String description;
    @ManyToOne
    private Blog blog;
}


/*

CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,

  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `blog_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
   `create_time` timestamp NULL DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` int DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkap39f76wn135k7ru564fbjb7` (`blog_id`),
  KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`),
  CONSTRAINT `FKkap39f76wn135k7ru564fbjb7` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ;

*/