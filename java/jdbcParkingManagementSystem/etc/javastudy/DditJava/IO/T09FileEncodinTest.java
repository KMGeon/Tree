package HighJava.src.JavaIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class T09FileEncodinTest {
    public static void main(String[] args) {
        /*
        인코딩 방식...

        한글 인코딩 방식은 크게 UTF-8과 EUC-KR 두가지로 나누어 볼 수 있다.
        원래 한글 윈도우는 CP949방식을 사용했는데 , 윈도우를 개발한 마이크로소프트에서
        EUC-KR방식에서 확장하였기 때문에 MS949라고도 부른다.
        한글 WINDOW의 메모장에서 말하는 ANSI 인코딩이란 CP949(Code Page 949)를 말한다.
        -MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
        -UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자: 1BYTE 한글: 3BYTE)->가변적
        -US-ASCII =>영문전용 인코딩 방식

        ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 나중에 여기에
        EUC-KR , CP949 라는 식으로 한글이 포함된 방식이 생겨났음.
         */

        FileInputStream fis = null;
        InputStreamReader isr = null;

        try {
            fis = new FileInputStream("E:\\D_Others/8.txt");
            //파일 인코딩을 이용하여 읽어오기 , InputStreamReader 객체는 파일 인코딩 방식을 지정할 수 있다.
            // 형식) new InputStreamReader(바이트기반스트림객체 , 인코딩방식);
            isr = new InputStreamReader(fis, "UTF-8");
            int data = 0;
            while ((data = isr.read()) != -1) {
                out.print((char) data);
            }
            out.println();
            out.println("출력 끝...");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
