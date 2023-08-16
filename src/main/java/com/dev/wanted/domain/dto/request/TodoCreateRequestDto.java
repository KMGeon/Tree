package com.dev.wanted.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDto {
    @NotBlank(message = "제목을 입력하세요")
    private String name;
    @NotBlank(message = "설명을 입력하세요")
    private String description;
}
