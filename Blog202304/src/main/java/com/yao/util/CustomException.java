package com.yao.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jack Yao on 2023/4/3 9:06 PM
 */
@Data
public class CustomException extends Exception{
  public static class TypeException extends Exception{
    public TypeException() {
    }

    public TypeException(String message) {
      super(message);
    }
  }

  public static class TagException extends Exception{
    public TagException() {
    }

    public TagException(String message) {
      super(message);
    }
  }
}
