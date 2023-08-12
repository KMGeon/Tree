package study.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {
    @Test
    public void contextLoad() throws Exception {
        HelloController helloController = new HelloController();
        helloController.hello();

    }
}