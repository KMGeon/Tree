package com.joy.playground.domain.controller;

import com.joy.playground.domain.application.UserService;
import com.joy.playground.domain.dto.request.UserRequest;
import com.joy.playground.domain.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse userCreate(
            @RequestBody @Valid UserRequest userRequest
    ) {
        return this.userService.create(userRequest);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequest userRequest
    ) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
    }

}
