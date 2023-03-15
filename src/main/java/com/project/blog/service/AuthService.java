package com.project.blog.service;

import com.project.blog.domain.User;
import com.project.blog.dto.request.Login;
import com.project.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;

    public void signIn(Login login){
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(IllegalArgumentException::new);
        user.addSession();
    }
}
