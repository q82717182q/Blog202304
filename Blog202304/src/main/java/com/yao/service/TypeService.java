package com.yao.service;

import com.yao.entity.Blog;
import com.yao.entity.Type;
import com.yao.repository.BlogRepository;
import com.yao.repository.TypeRepository;
import com.yao.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> findAllTypes() throws Exception{
        return typeRepository.findAll();
    }
    public void saveType(Type type) throws Exception {
        if (typeRepository.findByName(type.getName()) != null){
            throw new CustomException.TypeException("Type already exists.");
        } else if (type.getName().trim().isEmpty()) {
            throw new CustomException.TypeException("Type can't be empty.");
        } else {
            typeRepository.save(type);
        }
    }
}
