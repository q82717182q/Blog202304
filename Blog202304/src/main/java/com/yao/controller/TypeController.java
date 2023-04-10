package com.yao.controller;

import com.yao.entity.Tag;
import com.yao.entity.Type;
import com.yao.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@RestController
@Slf4j
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<?> findAllTypes(){
        try {
            return ResponseEntity.ok(typeService.findAllTypes());
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> findTypesWithPageSize(@RequestParam Integer page, Integer size){
        try{
            return ResponseEntity.ok(typeService.findTypesWithPageSize(page, size));
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @PostMapping
    public ResponseEntity<?> saveType(@RequestBody Type type){
        try {
            typeService.saveType(type);
            return ResponseEntity.ok("Type saved successfully.");
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/update/{typeId}")
    public ResponseEntity<?> saveType(@PathVariable("typeId") Integer typeId, @RequestBody Type type){
        try {
            typeService.updateType(typeId, type.getName());
            return ResponseEntity.ok("Type update successfully.");
        }catch (Exception e){
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


