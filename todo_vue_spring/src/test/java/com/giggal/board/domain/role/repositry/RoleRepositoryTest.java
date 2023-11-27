package com.giggal.board.domain.role.repositry;

import com.giggal.board.domain.role.entity.Role;
import com.giggal.board.domain.role.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        Role role = Role.builder()
                .roleId(1L)
                .name(RoleEnum.CREW.getRoleName())
                .build();

        roleRepository.save(role);
    }

    @Test
    @DisplayName("Repository 권한 찾기")
    public void findRoleWithValid() throws Exception {
        //given

        //when
        roleRepository.findByName(RoleEnum.CREW.getRoleName());
        //Then
        assertThat(roleRepository.count()).isNotNull();
    }
}