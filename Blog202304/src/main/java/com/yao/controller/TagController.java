package com.yao.controller;

import com.yao.entity.Tag;
import com.yao.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@RestController
@Slf4j
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<?> findAllTags(){
        try{
            return ResponseEntity.ok(tagService.findAllTags());
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @GetMapping("/page")
    public ResponseEntity<?> findTagsWithPageSize(@RequestParam Integer page, Integer size){
        try{
            return ResponseEntity.ok(tagService.findTagsWithPageSize(page, size));
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> saveTag(@RequestBody Tag tag){
        try {
            tagService.saveTag(tag);
            return ResponseEntity.ok("Tag saved successfully.");
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{tagId}")
    public ResponseEntity<?> saveTag(@PathVariable("tagId") Integer tagId, @RequestBody Tag tag){
        try {
            tagService.updateTag(tagId, tag.getName());
            return ResponseEntity.ok("Tag update successfully.");
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
