package com.yao.service;

import com.yao.entity.Type;
import com.yao.repository.TypeRepository;
import com.yao.util.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  public List<Type> findAllTypes() throws Exception {
    return typeRepository.findAll();
  }

  public void saveType(Type type) throws Exception {
    if (typeRepository.findByName(type.getName()) != null) {
      throw new CustomException.TypeException("Type already exists.");
    } else if (type.getName().trim().isEmpty()) {
      throw new CustomException.TypeException("Type can't be empty.");
    } else {
      typeRepository.save(type);
    }
  }

  public Page<Type> findTypesWithPageSize(Integer page, Integer size) throws Exception {
    Pageable paging = PageRequest.of(page, size);
    Page<Type> pagedResult = typeRepository.findAll(paging);
    if (pagedResult.hasContent()) {
      return pagedResult;
    } else {
      throw new CustomException.TypeException("There are no types now.");
    }
  }

  public Type updateType(Integer id, String name){
    Type type = new Type();
    type.setId(id);
    type.setName(name);
    Type updated =  typeRepository.save(type);
    return updated;
  }
}
