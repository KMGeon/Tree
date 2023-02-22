package com.example.hellospring;

import com.example.hellospring.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class HelloTest {

    @Test
    public void 롬북() throws Exception{
        //given
        String name= "test";
        int amount = 1000;
        //when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);
        //Then
        Assertions.assertThat(helloResponseDto.getName()).isEqualTo(name);
        Assertions.assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

}