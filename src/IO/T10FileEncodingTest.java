package HighJava.src.JavaIO;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
OutputStreamWriter 객체 -> outputStream(바이트 기반 출력용 스트림 객체)를 writer(문자기반의 출력용 객체)로 변환하는
보조 스트림 객체 -> 이 객체도 출력할 때 인코딩 방식을 지정해서 출력할 수 있다.
 */
public class T10FileEncodingTest {
    public static void main(String[] args) throws IOException {
        //키보드로 입력한 내용을 파일로 저장하는 데 out-utf8.txt파일은
        //utf-8 인코딩 방식으로out_ansi.txt파일은 'ms949' 인코딩 방식으로 저장한다.

        InputStreamReader isr = new InputStreamReader(System.in);

        //파일 출력용
        FileOutputStream fos1 = new FileOutputStream("E:\\D_Others/opps-ansi.txt");
        FileOutputStream fos2 = new FileOutputStream("E:\\D_Others/8.txt");

        //바이트를 -> 문자 기반으로 바꿔줌
        //장점 : 저장할 때 인코딩 방식으로 저장 가능
        OutputStreamWriter osw1 = new OutputStreamWriter(fos1, StandardCharsets.UTF_8);
        OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");

        int data = 0;
        System.out.println("아무거나 입력하세요.");
        while ((data = isr.read()) != -1) {
            osw1.write(data);
            osw2.write(data);
        }


    }
}
