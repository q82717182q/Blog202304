package com.yao.service;

import com.yao.entity.Tag;
import com.yao.repository.TagRepository;
import com.yao.util.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Jack
 * @date 2023/4/2
 * */
@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<Tag> findAllTags() throws Exception {
    return tagRepository.findAll();
  }

  public Page<Tag> findTagsWithPageSize(Integer page, Integer size) throws Exception {
    Pageable paging = PageRequest.of(page, size);
    Page<Tag> pagedResult = tagRepository.findAll(paging);
    if (pagedResult.hasContent()) {
      return pagedResult;
    } else {
      throw new CustomException.TagException("There are no tags now.");
    }
  }

  public void saveTag(Tag tag) throws Exception {
    if (tagRepository.findByName(tag.getName()) != null) {
      throw new CustomException.TagException("Tag already exists.");
    } else if (tag.getName().trim().isEmpty()) {
      throw new CustomException.TagException("Tag can't be empty.");
    } else {
      tagRepository.save(tag);
    }
  }

  public void updateTag(Integer tagId, String name) throws Exception {
    Tag tag = new Tag();
    tag.setId(tagId);
    tag.setName(name);
    tagRepository.save(tag);
  }
}
