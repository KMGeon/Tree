package dev.test.aswemake.global.initializer;

import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.entity.role.Role;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(
            RoleRepository roleRepository,
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role user = Role.builder()
                        .roleId(1L)
                        .name(RoleEnum.USER.getRoleName())
                        .build();

                Role market = Role.builder()
                        .roleId(2L)
                        .name(RoleEnum.MARKET.getRoleName())
                        .build();

                roleRepository.save(user);
                roleRepository.save(market);
            }
            if (memberRepository.count() == 0) {

                Optional<Role> adminRoleOptional = roleRepository.findByName(RoleEnum.MARKET.getRoleName());

                if (adminRoleOptional.isPresent()) {
                    Role adminRole = adminRoleOptional.get();

                    Member member = Member.builder()
                            .email("market@market.com")
                            .password(passwordEncoder.encode("market1234!"))
                            .roles(Collections.singleton(adminRole))
                            .build();

                    memberRepository.save(member);
                }
            }

        };
    }
}
