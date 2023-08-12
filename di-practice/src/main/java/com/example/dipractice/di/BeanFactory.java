package com.example.dipractice.di;

import com.example.dipractice.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.reflections.ReflectionUtils.getAllConstructors;
import static org.reflections.ReflectionUtils.withAnnotation;


public class BeanFactory {
    //class 타입 객체
    private Set<Class<?>> preInstantiatedBeans;

    //class 타입을 키 인스턴스를 value
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedBeans) {
        this.preInstantiatedBeans = preInstantiatedBeans;
        initialize();
    }

    // class 타입 객체를 가지고 인스턴스를 가지고 초기화
    public void initialize() {
        for (Class<?> clazz : preInstantiatedBeans) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> concreteClass) {
        //생성자
        Constructor<?> constructor = findConstructor(concreteClass);

        //파라미터
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : Objects.requireNonNull(constructor).getParameterTypes()) {
            parameters.add(getBean(typeClass));
        }

        //인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    //inject가 붙은 어노테이션을 가져온다.
    private Constructor<?> findConstructor(Class<?> concreteClass) {
        Constructor<?> constructor = getInjectedConstructor(concreteClass);

        if (Objects.nonNull(constructor)) {
            return constructor;
        }

        return concreteClass.getConstructors()[0];
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }

    public  Constructor<?> getInjectedConstructor(Class<?> clazz) {
        Set<Constructor> injectedConstructors = getAllConstructors(clazz, withAnnotation(Inject.class));
        if (injectedConstructors.isEmpty()) {
            return null;
        }
        return injectedConstructors.iterator().next();
    }
}