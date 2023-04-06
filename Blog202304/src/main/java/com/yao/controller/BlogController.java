package com.yao.controller;

import com.yao.entity.*;
import com.yao.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@RestController
@Slf4j
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<?> saveBlog(@RequestBody Blog blog){
        try {
            blogService.saveBlog(blog);
        }catch (Exception e){
            log.error("", e);
        }
        return ResponseEntity.ok("save blog success");
    }

    @GetMapping("/input")
    public ResponseEntity<?> inputBlog(){
        //todo 返回type 與 tag以提供下拉選單  可以做兩隻？
        return ResponseEntity.ok("");
    }

}
