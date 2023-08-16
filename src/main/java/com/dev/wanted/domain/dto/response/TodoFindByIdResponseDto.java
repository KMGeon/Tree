package com.dev.wanted.domain.dto.response;

import com.dev.wanted.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoFindByIdResponseDto {
    private Long id;
    private String name;
    private String description;

    public static TodoFindByIdResponseDto of(Todo todo) {
        return TodoFindByIdResponseDto.builder()
                .id(todo.getId())
                .name(todo.getName())
                .description(todo.getDescription())
                .build();
    }
}
