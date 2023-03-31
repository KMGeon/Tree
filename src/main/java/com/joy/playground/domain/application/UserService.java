package com.joy.playground.domain.application;

import com.joy.playground.domain.domain.User;
import com.joy.playground.domain.dto.request.UserRequest;
import com.joy.playground.domain.dto.response.UserResponse;
import com.joy.playground.domain.repository.UserRepository;
import com.joy.playground.global.exception.UserEmailDuplicationException;
import com.joy.playground.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        String email = userRequest.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new UserEmailDuplicationException(email);
        }

        User user = userRepository.save(userRequest.from());
        return UserResponse.convertData(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = findByUser(id);

        user.updateUser(userRequest);
        return UserResponse.convertData(user);
    }

    @Transactional
    public User deleteUser(Long id){
        User user = findByUser(id);
        user.destroy();
        return user;
    }


    private User findByUser(Long id) {
        return userRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
