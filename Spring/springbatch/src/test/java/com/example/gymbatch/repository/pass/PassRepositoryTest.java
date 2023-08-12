package com.example.gymbatch.repository.pass;

import com.example.gymbatch.repository.packaze.PackageEntity;
import com.example.gymbatch.repository.packaze.PackageRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PassRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    @DisplayName("save")
    public void save() throws Exception{
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("피티 12주");
        packageEntity.setPeriod(10);
        //when
        packageRepository.save(packageEntity);
        //Then
        Assertions.assertThat(packageEntity.getPackageName()).isEqualTo("피티 12주");
    }


}