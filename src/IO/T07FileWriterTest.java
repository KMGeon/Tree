package HighJava.src.JavaIO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.System.*;

/*
FileWriter(문자기반스트림)예제

 */
public class T07FileWriterTest {
    public static void main(String[] args) throws RuntimeException, IOException {
        //사용자가 입력한 내용을 그대로 파일로 저장하기
//
//        char c1 = 'A'; //문자를 직접 일력
//        char C2 = 65; //10진수를 지정
//        char c3 = '\u0041'; //16진수로 지정(유니코드)
//
//        char c4 = '가';//문자 직접 입력
//        char c5 = '\uAC00';//16진수 (유니코드)
//        char c6 = 44032;//10진수
//
//        int uniCode = c1;
//
//
//        out.println(c1);
//        out.println(C2);
//        out.println(c3);
//        out.println(c4);
//        out.println(c5);
//        out.println(c6);
//        out.println(uniCode);

        //콘솔(표준 입출력장치)와 연결된 입력용 문자스트림 생성
        //inputstreamReader 스트림 -> 바이트 기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림이다.
        InputStreamReader isr = new InputStreamReader(System.in);
        FileWriter fw = null; //파일 출력용 문자기반 스트림

        try {
            //파일 출력용 문자 스트림 객체 생성
            fw = new FileWriter("E:\\D_Others/testChar.txt");
            int data = 0;
            out.println("아무거나 입력하세요");
            //콘솔에서 입력할 때 입력의 끝 표시는 ctrl+d키를 누르면 된다.
            while ((data = isr.read()) != -1) {
                fw.write((char) data);//콘솔에서 입력받은 값을 파일로 출력하기
            }
            out.println("작업 끝...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isr.close();
        }

    }
}
