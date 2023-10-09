package HighJava.src.Annotation;/*
Class 오브젝트(클래스 정보를 담고 있는)를 생성하기
@author pc-21
 */

public class T01ClassObjectCreationTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //방법 1 : Class.forName()메서드 이용하기
        Class<?> klass = Class.forName("Annotation.T01ClassObjectCreationTest");

        //방법2 : getClass()메서드 이용하기
        T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
        new T01ClassObjectCreationTest();

        //방법3: .Class이용하기
        klass = T01ClassObjectCreationTest.class;
    }
}
/*
Java reflection에 대해서.
1.리플렉션은 런타임 시점에 클래스 또는 맴버변수 , 메서드 , 생성자등에 대한정보를 가져오거나 수정할 수 있고 , 새로운 객체를 생성하거나
,메서드를 실행할 수 있다.(컴파일 시점에 해당 정보를 알수 없는 경우 (소스코드 부재)에 우용하게 사용될 수 있음)

2.reflection api는 java.lang.reflect패키지와 java.lang.class를 통해 제공된다.
3.java.lang.class의 주요 메서드
-getName(),getSuperClass(),getInterfaces(),getModifiers()등,
4.java.lang.reflecti 패키지의 주요 클래스
-field , method , constructor , modifier등.
 */
