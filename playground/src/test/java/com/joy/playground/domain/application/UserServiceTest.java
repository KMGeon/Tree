package com.joy.playground.domain.application;

import com.joy.playground.domain.domain.User;
import com.joy.playground.domain.dto.request.UserRequest;
import com.joy.playground.domain.dto.response.UserResponse;
import com.joy.playground.domain.enums.IdEnum;
import com.joy.playground.domain.repository.UserRepository;
import com.joy.playground.global.exception.UserEmailDuplicationException;
import com.joy.playground.global.exception.UserNotFoundException;
import org.assertj.core.api.Assertions;
import org.hibernate.boot.TempTableDdlTransactionHandling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository = mock(UserRepository.class);

    private static final String VALID_EMAIL = "success@email.com";
    private static final String VALID_PASSWORD = "1234";
    private static final String VALID_NAME = "김무건";

    private static final String EXISTED_EMAIL = "EXISTED@email.com";
    private static final String UPDATE_EMAIL = "UPDATE@email.com";

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        given(userRepository.save(any(User.class))).will(invocation -> {
            User source = invocation.getArgument(0);
            return User.builder()
                    .id(1L)
                    .email(source.getEmail())
                    .password(source.getPassword())
                    .name(source.getName())
                    .build();
        });

        //Todo:중복된 이메일 given
        given(userRepository.existsByEmail(EXISTED_EMAIL))
                .willThrow(new UserEmailDuplicationException(EXISTED_EMAIL));

        //Todo: 업데이트
        given(userRepository.findByIdAndDeletedIsFalse(IdEnum.Default.getId()))
                .willReturn(Optional.of(
                        User.builder()
                                .email(UPDATE_EMAIL)
                                .password(VALID_PASSWORD)
                                .name(VALID_NAME)
                                .build()
                ));

        given(userRepository.findByIdAndDeletedIsFalse(100L))
                .willThrow(new UserNotFoundException(100L));

        //Todo: delete
        given(userRepository.findByIdAndDeletedIsFalse(IdEnum.delete.getId()))
                .willReturn(Optional.empty());
    }

    @Test
    @DisplayName("POST_user_create")
    public void userCreateWithValid() throws Exception {
        //given
        UserRequest userRequest = UserRequest.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .name(VALID_NAME)
                .build();
        //when
        UserResponse user = userService.create(userRequest);
        //Then
        assertThat(user.getEmail()).isEqualTo(VALID_EMAIL);
        assertThat(user.getPassword()).isEqualTo(VALID_PASSWORD);
        assertThat(user.getName()).isEqualTo(VALID_NAME);
    }

    @Test
    @DisplayName("POST_User_Invalid")
    public void userCreateWithDuplicate() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .email(EXISTED_EMAIL)
                .password(VALID_PASSWORD)
                .name(VALID_NAME)
                .build();
        //when

        assertThatThrownBy(() -> userService.create(userRequest))
                .isInstanceOf(UserEmailDuplicationException.class)
                .hasMessage("User email is alreay existed: " + EXISTED_EMAIL);

        //Then
        verify(userRepository).existsByEmail(EXISTED_EMAIL);
    }

    @Test
    @DisplayName("Patch_user_Valid")
    public void userUpdateWithValid() throws Exception {
        //given
        UserRequest userRequest = UserRequest.builder()
                .email(UPDATE_EMAIL)
                .password(VALID_PASSWORD)
                .name(VALID_NAME)
                .build();
        //when
        UserResponse user = userService.updateUser(1L, userRequest);
        //Then
        assertThat(user.getEmail()).isEqualTo(UPDATE_EMAIL);
        assertThat(user.getPassword()).isEqualTo(VALID_PASSWORD);

        verify(userRepository).findByIdAndDeletedIsFalse(1L);
    }

    @Test
    @DisplayName("Patch_user_Invalid")
    public void userUpdateWithNotExistedId(){
        //given
        UserRequest userRequest = UserRequest.builder()
                .email(UPDATE_EMAIL)
                .password(VALID_PASSWORD)
                .name(VALID_NAME)
                .build();
        //when
        assertThatThrownBy(()->userService.updateUser(100L,userRequest))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found: 100");
        //Then
        verify(userRepository).findByIdAndDeletedIsFalse(100L);
    }

    @Test
    @DisplayName("delete_user_valid")
    public void deleteUserWithValid(){
        //when
        User user = userService.deleteUser(IdEnum.Default.getId());
        //Then
        assertThat(user.isDeleted()).isTrue();
    }

}