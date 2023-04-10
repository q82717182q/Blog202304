package com.yao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yao.entity.Tag;
import com.yao.entity.Type;
import com.yao.service.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jack Yao on 2023/4/9 3:32 PM
 */
@ExtendWith(SpringExtension.class)
class TagControllerTest {
  @Mock
  private TagService tagService;
  @InjectMocks
  private TagController tagController;
  List<Tag> tags;
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    tagController = new TagController(tagService);
    tags = Arrays.asList(
            new Tag("Java"),
            new Tag("Spring"),
            new Tag("Jpa")
    );
  }

  @Test
  @DisplayName("test findAllTags()")
  void findAllTags() throws Exception {

    Mockito.when(tagService.findAllTags()).thenReturn(tags);
    ResponseEntity<?> response = tagController.findAllTags();
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(tags, response.getBody());
    Mockito.verify(tagService, Mockito.times(1)).findAllTags();
  }

  @Test
  @DisplayName("test findTagsWithPageSize()")
  void findTagsWithPageSize() throws Exception {

    Mockito.when(tagService.findTagsWithPageSize(1, 10)).thenReturn(new PageImpl<>(tags));
    ResponseEntity<?> response = tagController.findTagsWithPageSize(1, 10);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(tags, ((Page<Tag> )response.getBody()).getContent());
    Mockito.verify(tagService, Mockito.times(1)).findTagsWithPageSize(1,10);

  }

  @Test
  @DisplayName("test saveTag()")
  void saveTag() throws Exception {
    Tag tag = new Tag();
    tag.setName("test tag controller");
    Mockito.doNothing().when(tagService).saveTag(Mockito.any(Tag.class));

//    mockMvc.perform(MockMvcRequestBuilders.post("/tag")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsBytes(tag)))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.content().string("Tag saved successfully."))
//            .andDo(MockMvcResultHandlers.print());
  }

}