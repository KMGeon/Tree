package com.fastcampus.ch3.diCopy.diCopy1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Car{}
class SprotsCar extends Car{}
class Truck extends Car{}
class Engine{}


public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        System.out.println("car"+car);
        System.out.println("Engine"+engine);
    }

    static Object getObject(String key) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.txt"));

        Class clazz = Class.forName(properties.getProperty(key));
        return (Car)clazz.newInstance();
    }


}
