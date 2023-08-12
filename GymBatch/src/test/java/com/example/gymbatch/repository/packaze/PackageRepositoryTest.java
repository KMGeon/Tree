package com.example.gymbatch.repository.packaze;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    @DisplayName("생성")
    public void save() throws Exception{
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 챌린지 PT 12주");
        packageEntity.setPeriod(84);
        //when
        packageRepository.save(packageEntity);
        //Then
        assertNotNull(packageEntity.getPackageSeq());
    }

    @Test
    @DisplayName("일정 기간 이후에 가져오기")        
    public void findByCreatedAfter() throws Exception{
        //given
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity p1 = new PackageEntity();
        p1.setPackageName("학생 전용 3개월");
        p1.setPeriod(90);
        packageRepository.save(p1);

        PackageEntity p2 = new PackageEntity();
        p2.setPackageName("학생 전용 6개월");
        p2.setPeriod(90);
        packageRepository.save(p2);
        //when
        final List<PackageEntity> packageEntityList = packageRepository.findByCreatedAtAfter(localDateTime, PageRequest.of(0,1, Sort.by("packageSeq").descending()));

        for (PackageEntity packageEntity : packageEntityList) {
            System.out.println("packageEntity = " + packageEntity.getPackageName());
        }
        //Then
        assertThat(packageEntityList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("update")
    public void update() throws Exception{
        //given
        PackageEntity packageEntity =PackageEntity.builder()
                .packageName("바디프로필 이벤트 4개월")
                .period(90)
                .build();
        packageRepository.save(packageEntity);
        //when
        //Then
        //Assertions.assertThat().isEqualTo();
    }


}