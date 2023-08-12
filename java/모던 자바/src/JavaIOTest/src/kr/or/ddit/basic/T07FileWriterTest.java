package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * FileWriter(문자기반스트림) 예제
 * 
 * */
public class T07FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
//		char c1 = 'A';		// 문자를 직접 입력
//		char c2 = 65;		// 10진수를 지정
//		char c3 = '\u0041'; // 16진수로 지정(유니코드)
//		
//		char c4 = '가';		// 문자를 직접 입력
//		char c5 = '\uAC00'; // 16진수로 지정(유니코드)
//		char c6 = 44032;	// 10진수를 지정
//		
//		int unicode = c1;	// 유니코드 얻기
//		
//		System.out.println(c1);
//		System.out.println(c2);
//		System.out.println(c3);
//		System.out.println(c4);
//		System.out.println(c5);
//		System.out.println(c6);
//		System.out.println(unicode);
		
		// 콘솔(표준 입출력 장치)와 연결된 입력용 문자스트림 생성
		// InputStreamReader 스트림 => 바이트 기반 스트림을 문자기반 스트림으로 
		// 							 변환해주는 보조스트림
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; // 파일 출력용 문자 기반 스트림
		
		try {
			// 파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("e:/D_Other/testChar.txt");
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			
			// 콘솔에서 입력할 때 입력의 끝표시는 Ctrl + Z키를 누르면 된다.
			while ((data = isr.read()) != -1) {
				fw.write(data); // 콘솔에서 입력받은 값을 파일에 출력하기
			}
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				fw.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
