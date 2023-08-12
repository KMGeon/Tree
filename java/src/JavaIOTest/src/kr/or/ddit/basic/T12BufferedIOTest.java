package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

/*
 *  문자기반 스트림을 위한 Buffered스트림 사용 예제
 * 
 * */

public class T12BufferedIOTest {
	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
		// 이클립스에서 만든 자바프로그램이 실행되는 기본위치는 
		// 해당 '프로젝트폴더'가 기본 위치가 된다.
			fr = new FileReader("./src/kr/or/ddit/basic/T11BufferedIOTest.java");
			
			// 버퍼를 안 준것
			int data = 0;
			while((data = fr.read()) != -1) {
				System.out.print((char)data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
