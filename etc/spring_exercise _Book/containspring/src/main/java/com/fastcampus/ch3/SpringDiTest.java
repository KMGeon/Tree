package com.fastcampus.ch3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;



@Component
@Getter
@Setter
@ToString
class Car{

    @Value("red") String color;
    @Value("100") int oil;
      Engine engine;
     Door[] doors;

//     @Autowired
    public Car(@Value("red") String color, @Value("100") int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }
}
 @Component class Engine{}
@Component class SuperEngine extends  Engine{}
@Component class TurboEngine extends Engine{}
@Component class Door{}



public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config1.xml");

        Car car = (Car) ac.getBean("car");//byName
//        Car car =  ac.getBean("car",Car.class);//byName , 위와 같다. 타입을 줘서 형변환 x
        Car car2 = (Car)ac.getBean(Car.class);//byName


        Engine engine = (Engine) ac.getBean("engine"); //타입이 여러개이면 byname사용불가
        Door door = (Door) ac.getBean("door");

//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door",Door.class),ac.getBean("door",Door.class)});
        System.out.println(car);
        System.out.println("fdjksl");

    }
}


