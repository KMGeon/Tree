package com.joy.playground.domain.dto.response;

import com.joy.playground.domain.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;

    private String password;

    private String name;

    public static UserResponse convertData(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

}
