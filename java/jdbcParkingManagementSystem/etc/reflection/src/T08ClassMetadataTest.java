package HighJava.src.Reflection;

import java.lang.reflect.Modifier;

public class T08ClassMetadataTest {
    public static void main(String[] args) {
        Class<?> clazz = SampleVo.class;

        System.out.println("심플클래스명:" + clazz.getSimpleName());
        System.out.println("클래스명" + clazz.getName());
        System.out.println("상위클래스명" + clazz.getSuperclass().getName());

        //패키지 정보 가져오기
        Package pkg = clazz.getPackage();
        System.out.println("패키지정보: " + pkg.getName());

        //해당 클래스에서 구현하고 있는 인터페이스 목록 가져오기
        Class<?>[] interfaces = clazz.getInterfaces();

        System.out.println("인터페이스 목록");
        for (Class<?> inf : interfaces) {
            System.out.print(inf.getName() + "ㅣ");
        }
        System.out.println();

        //클래스에 접근제어(flag bit값 반환됨 , -> 접근제어자 설정유무 정보)
        int modFlag = clazz.getModifiers();

        System.out.println("접근제어자: " + Modifier.toString(modFlag));


    }
}
