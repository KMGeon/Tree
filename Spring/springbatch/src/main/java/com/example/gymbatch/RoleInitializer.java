package com.example.gymbatch;

import com.example.gymbatch.repository.packaze.PackageEntity;
import com.example.gymbatch.repository.packaze.PackageRepository;
import com.example.gymbatch.repository.user.UserEntity;
import com.example.gymbatch.repository.user.UserRepository;
import com.example.gymbatch.repository.user.UserStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(
            PackageRepository packageRepository,
            UserRepository userRepository
    ) {
        return args -> {
            if (packageRepository.count() == 0) {
                UserEntity user1 = UserEntity.builder()
                        .userId("A1000000")
                        .userName("우영우")
                        .status(UserStatus.ACTIVE)
                        .phone("01011112222")
                        .build();
                userRepository.save(user1);

                UserEntity user2 = UserEntity.builder()
                        .userId("A1000001")
                        .userName("최수연")
                        .status(UserStatus.ACTIVE)
                        .phone("01033334444")
                        .build();
                userRepository.save(user2);

                UserEntity user3 = UserEntity.builder()
                        .userId("A1000002")
                        .userName("이준호")
                        .status(UserStatus.INACTIVE)
                        .phone("01055556666")
                        .build();
                userRepository.save(user3);

                UserEntity user4 = UserEntity.builder()
                        .userId("B1000010")
                        .userName("권민우")
                        .status(UserStatus.ACTIVE)
                        .phone("01077778888")
                        .build();
                userRepository.save(user4);

                UserEntity user5 = UserEntity.builder()
                        .userId("B1000011")
                        .userName("동그라미")
                        .status(UserStatus.INACTIVE)
                        .phone("01088889999")
                        .build();
                userRepository.save(user5);

                UserEntity user6 = UserEntity.builder()
                        .userId("B2000000")
                        .userName("한선영")
                        .status(UserStatus.ACTIVE)
                        .phone("01099990000")
                        .build();
                userRepository.save(user6);

                UserEntity user7 = UserEntity.builder()
                        .userId("B2000001")
                        .userName("태수미")
                        .status(UserStatus.ACTIVE)
                        .phone("01000001111")
                        .build();
                userRepository.save(user7);
            }

            if (packageRepository.count() == 0) {
                PackageEntity package1 = PackageEntity.builder()
                        .packageName("Starter PT 10회")
                        .count(10)
                        .period(60)
                        .build();
                packageRepository.save(package1);

                PackageEntity package2 = PackageEntity.builder()
                        .packageName("Starter PT 20회")
                        .count(20)
                        .period(120)
                        .build();
                packageRepository.save(package2);

                PackageEntity package3 = PackageEntity.builder()
                        .packageName("Starter PT 30회")
                        .count(30)
                        .period(180)
                        .build();
                packageRepository.save(package3);

                PackageEntity package4 = PackageEntity.builder()
                        .packageName("무료 이벤트 필라테스 1회")
                        .count(1)
                        .build();
                packageRepository.save(package4);

                PackageEntity package5 = PackageEntity.builder()
                        .packageName("바디 챌린지 PT 4주")
                        .period(28)
                        .build();
                packageRepository.save(package5);

                PackageEntity package6 = PackageEntity.builder()
                        .packageName("바디 챌린지 PT 8주")
                        .period(48)
                        .build();
                packageRepository.save(package6);

                PackageEntity package7 = PackageEntity.builder()
                        .packageName("인바디 상담")
                        .build();
                packageRepository.save(package7);
            }
        };
    }
}