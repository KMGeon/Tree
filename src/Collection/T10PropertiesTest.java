package HighJava.src.Collection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class T10PropertiesTest {
	public static void main(String[] args) throws Exception {
		// 키와 value가 둘다 string으로 구성되어야 사용 가능
		/*
		 * Properties는 map보다 축소된 기능의 객체라고 할수 있다 map은 모든 형태의 객체 데이터를 키와 value값으로 사용할 수
		 * 있지만 Properties는 키와 value값으로 string만 사용할 수 있다.
		 * 
		 * map은 put() , get() 메서드를 이용하여 데이터를 입출력하지만 Properties는 setProperty ,
		 * getProperty 메소드를 통하여 데이터 입출력한다.
		 */
		Properties prop = new Properties();

		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");

		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름: "+name);
		System.out.println("전화번호: "+tel);
		System.out.println("주소: "+prop.getProperty("addr"));

		// 내용을 파일로 저장하기
		prop.store(new FileOutputStream("src/kr/or/ddit/basic/test.properties"), "코멘트입니다.");
	}

}
