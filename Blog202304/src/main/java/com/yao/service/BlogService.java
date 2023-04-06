package com.yao.service;

import com.yao.entity.*;
import com.yao.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }
}
