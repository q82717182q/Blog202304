package com.yao.service;

import com.yao.entity.Tag;
import com.yao.repository.TagRepository;
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

import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jack Yao on 2023/4/7 8:15 PM
 */
class TagServiceTest {

  @Mock
  private TagRepository tagRepository;
  @InjectMocks
  private TagService tagService;

  private List<Tag> tagList;

  @BeforeEach
  public void setUp(){
    MockitoAnnotations.openMocks(this);
    Tag tag1 = new Tag();
    tag1.setId(1);
    tag1.setName("tag1");
    Tag tag2 = new Tag();
    tag2.setId(2);
    tag2.setName("tag2");
    tagList = Arrays.asList(tag1, tag2);
  }
  @Test
  @DisplayName("test findAllTags()")
  public void findAllTags() throws Exception {
    Mockito.when(tagRepository.findAll()).thenReturn(tagList);
    List<Tag> resultTags = tagService.findAllTags();
    Assertions.assertEquals(tagList.size(), resultTags.size());
    Assertions.assertEquals(tagList.get(0).getId(), resultTags.get(0).getId());
    Assertions.assertEquals(tagList.get(1).getName(), resultTags.get(1).getName());
  }

  @Test
  @DisplayName("test findTagsWithPageSize()" )
  public void findTagsWithPageSize() throws Exception {
    Page<Tag> expectedPage = new PageImpl<>(tagList);
    Mockito.when(tagRepository.findAll(Mockito.any(Pageable.class))).thenReturn(expectedPage);
    Page<Tag> actualPage = tagService.findTagsWithPageSize(1, 10);
    Assertions.assertEquals(expectedPage.getSize(), actualPage.getSize());
    Assertions.assertEquals(expectedPage.getContent().get(0).getId(), actualPage.getContent().get(0).getId());
    Assertions.assertEquals(expectedPage.getContent().get(1).getName(), actualPage.getContent().get(1).getName());
  }

  @Test
  @DisplayName("test findTagsWithPageSize() invalid input" )
  public void findTagsWithPageSize_InvalidInput() throws Exception {
    Page<Tag> page = new PageImpl<>(new ArrayList<>());
    Mockito.when(tagRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
    Assertions.assertThrows(CustomException.TagException.class, () -> tagService.findTagsWithPageSize(0,1));
  }


  @Test
  @DisplayName("test saveTag() duplicate")
  public void saveTag_duplicate() throws Exception {
    Tag tag = new Tag();
    tag.setId(1);
    tag.setName("tag1");
    Mockito.when(tagRepository.findByName(tag.getName())).thenReturn(tag);
    Assertions.assertThrows(CustomException.TagException.class, () -> {tagService.saveTag(tag);});
  }
  @Test
  @DisplayName("test saveTag() empty tag name")
  public void saveTag_empty_tag_name() throws Exception {
    Tag tag1 = new Tag();
    tag1.setId(1);
    tag1.setName("");
    Mockito.when(tagRepository.findByName(tag1.getName())).thenReturn(tag1);
    Assertions.assertThrows(CustomException.TagException.class, () -> {tagService.saveTag(tag1);});
    Tag tag2 = new Tag();
    tag2.setId(1);
    tag2.setName("      ");
    Mockito.when(tagRepository.findByName(tag2.getName())).thenReturn(tag2);
    Assertions.assertThrows(CustomException.TagException.class, () -> {tagService.saveTag(tag2);});
  }
  @Test
  @DisplayName("test saveTag()")
  public void saveTag() throws Exception {
    Tag tag = new Tag();
    tag.setId(1);
    tag.setName("tag1");
    tagService.saveTag(tag);
    Mockito.verify(tagRepository, Mockito.times(1)).save(tag);
  }
  @Test
  @DisplayName("test updateTag")
  public void updateTag() throws Exception {
    Tag tag = new Tag();
    tag.setId(1);
    tag.setName("tag1");
    tagService.updateTag(tag.getId(), tag.getName());
    Mockito.verify(tagRepository, Mockito.times(1)).save(tag);
  }

}