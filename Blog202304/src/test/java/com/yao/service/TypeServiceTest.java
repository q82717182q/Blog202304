package com.yao.service;


import com.yao.entity.Type;
import com.yao.repository.TypeRepository;
import com.yao.util.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jack Yao on 2023/4/6 9:46 PM
 */
public class TypeServiceTest {

  /*

  在进行依赖注入时，通常有一个主要的对象，需要将其他对象或依赖项注入到这个主要对象中，以便在测试中使用。因此，需要先确定哪个是主要对象，哪些是需要注入到主要对象中的依赖项。
在单元测试中，通常使用模拟对象来模拟外部依赖项的行为。因此，需要先确定哪些外部依赖项需要模拟，然后使用 @Mock 注解来创建模拟对象。接下来，需要确定哪个对象需要使用这些模拟对象作为依赖项，并使用 @InjectMocks 注解将这些模拟对象注入到需要测试的对象中。
需要注意的是，@InjectMocks 注解只能注入被 @Mock 注解所创建的模拟对象，如果需要注入其他对象或依赖项，应该使用其他依赖注入框架来完成注入操作。同时，在进行依赖注入时，还需要考虑对象之间的依赖关系，避免循环依赖或注入出错的情况。
   */
  @Mock
  private TypeRepository typeRepository;

  @InjectMocks
  private TypeService typeService;

  private List<Type> typeList;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    Type type1 = new Type();
    type1.setId(1);
    type1.setName("Type 1");

    Type type2 = new Type();
    type2.setId(2);
    type2.setName("Type 2");

    typeList = Arrays.asList(type1, type2);
  }
  @Test
  @DisplayName("Test findAllTypes()")
  public void testFindAllTypes() throws Exception {
    Mockito.when(typeRepository.findAll()).thenReturn(typeList);
    List<Type> types = typeService.findAllTypes();
    Assertions.assertEquals(typeList.size(), types.size());
    Assertions.assertEquals(typeList.get(0).getId(), types.get(0).getId());
    Assertions.assertEquals(typeList.get(1).getName(), types.get(1).getName());
  }
  @Test
  @DisplayName("Test saveType() with valid input")
  public void testSaveTypeWithValidInput() throws Exception{
    Type type = new Type();
    type.setName("New Type");
//    Mockito.when(typeRepository.findByName(type.getName())).thenReturn(null);
    typeService.saveType(type);
    Mockito.verify(typeRepository, Mockito.times(1)).save(type);
  }

  @Test
  @DisplayName("Test saveType() with invalid input")
  public void testSaveTypeWithInvalidInput() throws Exception {
    Type existingType = new Type();
    existingType.setName("Existing Type");
    Mockito.when(typeRepository.findByName(existingType.getName())).thenReturn(existingType);

    Type emptyType = new Type();
    emptyType.setName("");
    Assertions.assertThrows(CustomException.TypeException.class, () -> {
      typeService.saveType(emptyType);
    });

    Type duplicateType = new Type();
    duplicateType.setName("Existing Type");
    Assertions.assertThrows(CustomException.TypeException.class, () -> {
      typeService.saveType(duplicateType);
    });
  }

  @Test
  @DisplayName("Test findTypesWithPageSize()")
  public void findTypesWithPageSize() throws Exception {
    int page = 0;
    int size = 10;
    Page<Type> typePage = new PageImpl<>(typeList);
    Mockito.when(typeRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(typePage);
    /*Q:所以這邊用到Mockito.any(PageRequest.class)，是因為這邊傳什麼參數不重要嗎？
      A:是的，這邊使用了 Mockito.any(PageRequest.class) 表示只要參數是 PageRequest 型別的任何值都會回傳 typePage。這樣寫的目的是避免在測試時受到傳入參數的影響而產生錯誤的結果，同時也可以測試方法是否正確呼叫了 typeRepository.findAll() 方法。*/
    Page<Type> pagedResult = typeService.findTypesWithPageSize(page,size);
    Assertions.assertEquals(typePage.getSize(), pagedResult.getSize());
    Assertions.assertEquals(typePage.getTotalElements(), pagedResult.getTotalElements());
    Assertions.assertEquals(typePage.getContent().get(0).getId(), pagedResult.getContent().get(0).getId());
    Assertions.assertEquals(typePage.getContent().get(1).getName(), pagedResult.getContent().get(1).getName());
  }

  @Test
  @DisplayName("Test updateType()")
  public void updateType() {
    Type type = new Type();
    type.setId(1);
    type.setName("test type");

    Type updatedType = new Type();
    updatedType.setId(type.getId());
    updatedType.setName("new test type");
    Mockito.when(typeRepository.findById(type.getId())).thenReturn(Optional.of(type));
    Mockito.when(typeRepository.save(Mockito.any(Type.class))).thenReturn(updatedType);

    Assertions.assertEquals("new test type",typeService.updateType(type.getId(), "new test type").getName());
  }
}