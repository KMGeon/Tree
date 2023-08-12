package com.test.junitpractice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
class JunitPracticeApplicationTest {
    @Test
    public void applcationTest() throws Exception{
        fail("test");
    }
}