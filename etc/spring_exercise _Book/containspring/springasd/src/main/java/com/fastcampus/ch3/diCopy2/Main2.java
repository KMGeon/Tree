package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car {
}

class SprotsCar extends Car {
}

class Truck extends Car {
}

class Engine {
}

class AppContext {
    Map map;

    AppContext() {
        try {
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            //properties에 저장된 내용을 Map에 저장
            map = new HashMap(p);
            
            //반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장
            for (Object key : map.keySet()) {
                Class clazz = Class.forName((String) map.get(key));
                map.put(key, clazz.newInstance()); //객체를 만들어줌
            }
        } catch (Exception e) {
         e.printStackTrace();
        }
    }
     

    Object getBean(String key) {
        return map.get(key);
    }
}


public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("car" + car);
        System.out.println("Engine" + engine);
    }


}
