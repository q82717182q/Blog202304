package com.yao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yao.entity.Blog;
import com.yao.repository.BlogRepository;
import com.yao.service.BlogService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Jack Yao on 2023/4/7 9:57 PM
 */
@ExtendWith(SpringExtension.class)// 使用JUnit 5來測試，並且啟用Spring框架支援
@SpringBootTest// 載入Spring應用的所有配置，並初始化Spring上下文環境
@AutoConfigureMockMvc// 自動配置MockMvc對象，用於測試控制器
public class BlogControllerTest {


  @Autowired// 自動注入MockMvc實例
  private MockMvc mockMvc;

  @Autowired// 自動注入ObjectMapper實例，用於序列化JSON
  private ObjectMapper objectMapper;

  @MockBean// 創建BlogService的Mock對象
  private BlogService blogService;

  public BlogControllerTest() {
  }
  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);// 打開Mockito框架的mock對象注入

  }
  @Test
  @DisplayName("test saveBlog()")
  public void testSaveBlog() throws Exception {
    // Arrange
    Blog blog = new Blog();
    blog.setTitle("Test Blog");
    blog.setContent("This is a test blog");

    doNothing().when(blogService).saveBlog(any(Blog.class));// 指定當調用blogService的saveBlog方法時，不做任何事情，避免真正的保存操作
  /*  If you don't use doNothing and you mock an object, the real method is not called
    If you don't use doNothing and you spy an object, the real method is called*/
//https://stackoverflow.com/questions/28836778/usages-of-dothrow-doanswer-donothing-and-doreturn-in-mockito

    // Act & Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/blog") // 模擬一個post請求，路徑是"/blog"
                    .contentType(MediaType.APPLICATION_JSON_VALUE) // 設定content type為application/json
                    .content(objectMapper.writeValueAsString(blog))) // 將blog物件序列化為JSON格式，作為請求的body
            .andExpect(MockMvcResultMatchers.status().isOk()) // 斷言返回的HTTP狀態碼應該是200
            .andExpect(MockMvcResultMatchers.content().string("save blog success")) // 斷言返回的內容應該是"save blog success"
            .andDo(print()); // 執行請求並輸出結果
  }


}