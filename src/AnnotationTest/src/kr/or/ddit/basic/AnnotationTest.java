package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) {
		
		// Reflection API를 이용하여 선언된 메소드 목록 가져오기
		
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName());
			
			// annotation 정보 가져오기
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for(Annotation anno : annos) {
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0; i<printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}
				System.out.println();
			}
		}
	}
}
