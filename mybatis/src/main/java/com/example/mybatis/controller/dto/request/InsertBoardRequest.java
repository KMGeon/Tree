package com.example.mybatis.controller.dto.request;

import com.example.mybatis.controller.validGroup.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class InsertBoardRequest {
    @NotBlank(message = "title 필드는 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    private String title;
    @NotBlank(message = "content 필드는 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    private String content;
    @NotBlank(message = "writer 필드는 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    private String writer;
}
