package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *  언오테이션에 대하여...
 *  
 *  프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것
 *  (JDK 1.5부터 지원)
 *  주석처럼 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함
 *  
 *  종류  1. 표준 언노테이션(주로 컴파일러에게 유용한 정보를 제공하기 위한 언노테이션)
 *  	2. 매타 언노테이션(언노테이션을 위한 언노테이션, 즉 언노테이션을 정의할 때 사용하는 언노테이션)
 *  
 *  언노테이션 타입 정의하기
 *  @interface 언노테이션 이름 {
 *  	요소타입 타입요소이름(); // 반환값이있고 매개변수는 없는 추상메서드의 형태
 *  	...
 *  }
 *  
 *  언노테이션 요소의 규칙
 *  1. 요소의 타입은 기본형, String, enum, annotation, class만 허용된다.
 *  2. ()안에 매개변수를 선언할 수 없다.
 *  3. 예외를 선언할 수 없다.
 *  4. 요소의 타입에 타입 매개변수 (제네릭타입글자)를 사용할 수 없다.
 *  
 *  
 * */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {
	String value() default "-"; // default 는 옵션
	int count() default 20;
}
