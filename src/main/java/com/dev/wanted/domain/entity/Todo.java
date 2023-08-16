package com.dev.wanted.domain.entity;

import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void updateTodo(UpdateTodoRequestDto updateTodoRequestDto) {
        this.name = updateTodoRequestDto.getName();
        this.description = updateTodoRequestDto.getDescription();
    }

    @Builder
    public Todo(Long id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
