package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05FileStreamTest {
	public static void main(String[] args) {
		
		// 파일 읽기위한 스트림 객체 생성
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("e:/D_Other/test.txt");
			
			int data;
			// 읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다.
			while((data = fis.read()) != -1) {
				// 읽어온 자료 출력하기
				System.out.print((char)data);
			}
			
			fis.close(); // 작업 완료 후 스트림 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
