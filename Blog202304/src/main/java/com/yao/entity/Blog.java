package com.yao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@Entity
@Table(name = "blog")
@Data
public class Blog extends AbstractBaseEntity {
    private String title;
    @Basic(fetch = FetchType.LAZY)/*只在用到時才載入關聯的物件，get此物件的時候才用*/
    @Lob
    private String content;
    private String description;
    private String firstPicture;
    private String flag;
    private Integer views;              /*觀看數*/
    private boolean appreciation;       /*讚賞數*/
    private boolean shareStatement;     /*轉載聲明是否開啟*/
    private boolean commentable;      /*評論開啟*/
    private boolean published;          /*是否發布*/
    private boolean recommend;          /*是否推薦*/
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne
    private Type type;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;
}


/*
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,

  `appreciation` bit(1) NOT NULL,
  `commentable` bit(1) NOT NULL,
  `content` longtext,
  `description` varchar(255) DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `views` int DEFAULT NULL,

  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_at` timestamp NULL DEFAULT NULL,
  `last_modified_by` int DEFAULT NULL,
  `version` int DEFAULT NULL,
    PRIMARY KEY (`id`)

)

*/