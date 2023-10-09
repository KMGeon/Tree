package tobyspring.boot.springboot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    @GetMapping("/hello")
    public String hello(String name){
        return "Hello"+name;
    }
}
