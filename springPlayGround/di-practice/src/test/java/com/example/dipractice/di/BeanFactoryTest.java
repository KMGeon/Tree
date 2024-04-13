package com.example.dipractice.di;

import com.example.dipractice.controller.Controller;
import com.example.dipractice.controller.UserController;
import com.example.dipractice.service.Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        //reflection 대상
        reflections = new Reflections("com.example.dipractice");
        // controller, service 조회
        Set<Class<?>> typesAnnotatedWith = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(typesAnnotatedWith);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

    @Test
    @DisplayName("diTest")
    public void diTest() throws Exception {
        //given
        UserController userController = beanFactory.getBean(UserController.class);
        //when
//        System.out.println("userController = " + userController);
        System.out.println("userController = " + userController.getUserService());
        System.out.println("userController = " + userController.getClass());
        //Then
        Assertions.assertThat(userController).isNotNull();
    }

}