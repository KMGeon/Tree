package HighJava.src.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
    public static <PrintAnnotation> void main(String[] args) {
        /*
            Reflection api를 이용하여 선언된 메서드 목록 가져오기
         */
        Method[] declaredMethods = Service.class.getDeclaredMethods();

        for (Method m : declaredMethods
        ) {
            System.out.println(m.getName());
            //Annotation정보 가져오기
            Annotation[] annos = m.getDeclaredAnnotations();
            for (Annotation anno : annos
            ) {
                if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
                    PrintAnnotation printAnno = (PrintAnnotation) anno;
                    // System.out.println("value값"+printAnno.value());
                    // System.out.println("count값 : "+printAnno.count);
                }
            }
        }
    }
}
