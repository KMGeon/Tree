package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) {
		
		byte[] inSrc = {0, 1, 2, 3, 4 , 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		// 직접 복사
//		outSrc = new byte[inSrc.length];
//		for(int i=0; i<inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
//		
//		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
		
		// arraycopy를 이용한 배열 복사 방법
//		outSrc = new byte[inSrc.length]; // 배열 공간 확보
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length); // 파라미터 값 잘 보기
//		
//		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
		
		// 스트림 객체 생성하기
		// 데이터를 읽어오기 위한 input 
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data; // 읽어온 바이트 데이터를 저장할 변수
		
		// read() 메소드 => byte단위로 자룔를 읽어와 int 형으로 반환한다.
		// 				  더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) {
			baos.write(data); // 출력하기
		}
		
		// 출력된 스트림 값들을 배열로 변환해서 반환하는 메소드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		
		
		
	}
}
