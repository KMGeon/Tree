package Reflection;

import javafx.scene.effect.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class T03MehodMetadataTest {
    public static void main(String[] args) throws ClassNotFoundException {

        //class객체 생성하기
        Class<?> clazz = Class.forName("Reflection.SampleVo");

        //클래스에 선언된 모든 메서드의 메타데이터 정보 가져오기
        Method[] methodArr = clazz.getDeclaredMethods();

        for (Method m : methodArr) {
            System.out.println("메서드 명" + m.getName());
            System.out.println("메서드 리턴타입" + m.getReturnType());
            //해당 메서드의 접근제어자 정보 가져오기
            int modFlag = m.getModifiers();
            System.out.println("메서드 접근제어자:" + Modifier.toString(modFlag));
            Class<?>[] paramArr = m.getParameterTypes();
            System.out.println("파라미터 정보");
            for (Class<?> klass : paramArr) {
                System.out.println(klass.getName() + "ㅣ");
            }
            System.out.println();

            //해당 메서드가 던지는 예외타입 가져오기
            Class<?>[] exTypeArr = m.getExceptionTypes();
            System.out.println("메서드에서 던지는 예외타입 정보");
            for (Class<?> klass : exTypeArr) {
                System.out.println(klass.getName() + "l");
            }
            System.out.println();

            //해당 메서드에 존재하는 annotation타입 정보 가져오기
            Annotation[] annos = m.getDeclaredAnnotations();
            System.out.println("어노테이션 타입 정도");
            for (Annotation anno : annos
            ) {
                System.out.println(anno.annotationType().getName() + "l");
            }
            System.out.println();
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }

    }
}
