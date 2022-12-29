package HighJava.src.JavaIO;

import java.io.*;
import java.lang.System.*;

import static java.lang.System.out;
import static jdk.nashorn.internal.objects.Global.println;

/*
문자기반 스트림을 위한 buffer
 */
public class T12BufferedIOTest {
    public static void main(String[] args) throws IOException {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            //이클립스에서 만든 자바프로그램이 실행되는 기본 위치는 해당 프로젝트폴더가 기본위치가 된다.
            fr = new FileReader("BasicJava/HighJava/src/JavaIO/T12BufferedIOTest.java");
//            int data = 0;
//            while ((data = fr.read()) != -1) {
//                println((char) data);
//            }

//            bufferreader를 이용해서 할때
            br = new BufferedReader(fr);

            //한줄씩 읽을 수 있도록 해주는 readLine()을 이용한다.
            String temp = "";
            int cnt = 1;
            while ((temp = br.readLine()) != null) {
                String.format("% 4d: $s\n", cnt++, temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }
    }
}