package com.example.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //- Lombok API
@NoArgsConstructor
public class TestDTO {
   private int bookId;
   private String title;
   private  String category;
   private  int price;
   private Date insertDate;


   public TestDTO(int bookId, String title) {
      this.bookId = bookId;
      this.title = title;
   }
}