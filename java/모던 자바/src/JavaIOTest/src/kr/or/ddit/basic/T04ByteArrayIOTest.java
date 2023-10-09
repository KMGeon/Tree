package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0, 1, 2, 3, 4 , 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 데이터를 읽을때 사용할 배열
		
		// 스트림 객체 생성하기
		// 데이터를 읽어오기 위한 input
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len = 0; // 버퍼로 읽어드린 byte 수, 이걸 쓰는 이유 속도가 빨라진다. 단점은 버퍼를 키울 수록 메모리를 잡아먹는 것
		
		// read() 메소드 => byte단위로 자룔를 읽어와 int 형으로 반환한다.
		// 				  더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((len = bais.read(temp)) != -1) {
			
			System.out.println("temp => " + Arrays.toString(temp));
			
			System.out.println("len => " + len);
			baos.write(temp, 0, len); // 출력하기
		}
		
		// 출력된 스트림 값들을 배열로 변환해서 반환하는 메소드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		
		
		
	}
}
