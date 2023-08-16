package com.dev.wanted.global.initializer;

import com.dev.wanted.domain.entity.Todo;
import com.dev.wanted.domain.entity.User;
import com.dev.wanted.domain.repository.TodoRepository;
import com.dev.wanted.domain.repository.UserRepository;
import com.dev.wanted.domain.role.entity.Role;
import com.dev.wanted.domain.role.enums.RoleEnum;
import com.dev.wanted.domain.role.repositry.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TodoRepository todoRepository
    ) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role custom = Role.builder()
                        .roleId(1L)
                        .name(RoleEnum.USER.getRoleName())
                        .build();

                Role admin = Role.builder()
                        .roleId(2L)
                        .name(RoleEnum.ADMIN.getRoleName())
                        .build();

                roleRepository.save(custom);
                roleRepository.save(admin);
            }
        };
    }
}
