package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/*
 * 프린트 기능 제공 보조 스트림 예제 
 * */

public class T14PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("e:/D_Other/print.txt");
		
		/*
		 * PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 
		 * OutputStream의 서브클래스이다.
		 * 
		 * */
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream 입니다.\n");
		out.println("안녕하세요. PrintStream 입니다2.");
		out.println("안녕하세요. PrintStream 입니다3.");
		out.println(out); // 객체출력
		out.println(3.14);
		
		/*
		 * PrintWriter은 데이터를 문자로 출력하는 기능을 수행함. (System.out)
		 * 향상된 기능의 PrintWriter가 추가되었지만 계속 사용되고 있음.
		 * 
		 * PrintWriter가 PrintWriter보다 다양한 언어의 문자를 처리하는데 적합하다.
		 * */
		FileOutputStream fos2 = new FileOutputStream("e:/D_Other/print2.txt");
		// Servlet할 때 나온다.
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		
		pw.print("안녕하세요. PrintWriter 입니다.\n");
		pw.println("안녕하세요. PrintWriter 입니다2.");
		pw.println("안녕하세요. PrintWriter 입니다3.");
		
		pw.close();
		
	}
}
