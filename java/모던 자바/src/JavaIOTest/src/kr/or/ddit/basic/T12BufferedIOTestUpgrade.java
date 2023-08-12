package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
    문자기반 Stream을 위한 BufferedStream 사용 예제
 */
public class T12BufferedIOTestUpgrade {
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// 이클립스에서 만든 자바프로그램이 실행되는 기본 위치는
			// 해당 '프로젝트폴더' 가 기본 위치가 된다.
			fr = new FileReader("./src/kr/or/ddit/basic/T12BufferedIOTest.java");

			/*
			 * int data = 0; while((data =fr.read()) != -1) { System.out.print((char) data);
			 * }
			 */

			br = new BufferedReader(fr);

			// 한줄씩 읽을 수 있도록 해주는 readLine()을 이용한다.
			String temp = "";
			int cnt = 1;

			while ((temp = br.readLine()) != null) {
				System.out.printf("%4d : %s\n", cnt++, temp);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
