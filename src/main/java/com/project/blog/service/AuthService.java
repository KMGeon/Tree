package com.project.blog.service;

import com.project.blog.domain.Session;
import com.project.blog.domain.User;
import com.project.blog.dto.request.Login;
import com.project.blog.dto.request.SignUp;
import com.project.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public Long signIn(Login login){
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(IllegalArgumentException::new);
        Session session =user.addSession();
        return user.getId();
    }

    public void signUp(SignUp signUp){
        Optional<User> duplication = userRepository.findByEmail(signUp.getEmail());
        if (duplication.isPresent()){
            throw new IllegalArgumentException();
        }
        userRepository.save(signUp.toEntity());
    }
}
