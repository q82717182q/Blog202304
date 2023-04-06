package com.yao.entity;
/*
 * @author Jack
 * @date 2023/3/31
 * */

import lombok.*;

import javax.persistence.*;
import java.util.*;

@MappedSuperclass
@Data
public abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    protected Date updateTime;
    @Column(name = "update_by")
    protected Integer updateBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP",name = "create_time")
    protected Date createTime;
    @Column(name = "create_by")
    protected Integer createdBy;
    @Column(name = "version")
    protected Integer version;
    @Column(name = "deleted")
    protected Boolean deleted;

    @PrePersist
    public void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
    @PreUpdate
    public void onUpdate() {
        this.updateTime = new Date();
    }

//    public void updateBase(Integer userId){
//        LocalDateTime utcTime = LocalDateTime.now(ZoneOffset.UTC);
//        long utcTimestamp = utcTime.toInstant(ZoneOffset.UTC).toEpochMilli();
//        if(this.getId() == null || this.getId() == 0 ){
//            this.setVersion(0);
//            this.setCreatedTime(new Timestamp(utcTimestamp));
//            this.setCreatedBy(0);
//            this.setLastModifiedTime(new Timestamp(utcTimestamp));
//            this.setLastModifiedBy(0);
//        }else {
//            this.setVersion(this.getVersion() + 1);
//            this.setLastModifiedTime(new Timestamp(utcTimestamp));
//            this.setLastModifiedBy(userId);
//        }
//    }
}



  /*
  *
  *
CREATE TABLE `???` (
        `id` INT NOT NULL AUTO_INCREMENT,



       `last_modified_at` TIMESTAMP ,
        `last_modified_by` INT DEFAULT 0,
        `created_at` TIMESTAMP ,
        `created_by` INT DEFAULT 0,
        `version` INT DEFAULT 0,
        `deleted` BIT(1) DEFAULT 0,
        PRIMARY KEY (`id`),

   )
  *
  * */
