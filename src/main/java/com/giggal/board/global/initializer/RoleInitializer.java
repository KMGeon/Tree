package com.giggal.board.global.initializer;

import com.giggal.board.domain.role.entity.Role;
import com.giggal.board.domain.role.enums.RoleEnum;
import com.giggal.board.domain.role.repositry.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role CREW = Role.builder()
                        .roleId(1L)
                        .name(RoleEnum.CREW.getRoleName())
                        .build();

                Role TLeader = Role.builder()
                        .roleId(2L)
                        .name(RoleEnum.TLeader.getRoleName())
                        .build();

                Role PM = Role.builder()
                        .roleId(3L)
                        .name(RoleEnum.PM.getRoleName())
                        .build();

                Role ADMIN = Role.builder()
                        .roleId(4L)
                        .name(RoleEnum.ADMIN.getRoleName())
                        .build();

                roleRepository.save(CREW);
                roleRepository.save(TLeader);
                roleRepository.save(PM);
                roleRepository.save(ADMIN);
            }
        };
    }
}
