package HighJava.src.JavaIO;

import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.System.*;

public class T05FileStreamTest {
    public static void main(String[] args) {

        //파일 읽기위한 스트림 객체 생성
        FileInputStream fis;
        try {
            fis = new FileInputStream("E:/D_Others/test.txt");
            int data;
            //읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미
            while ((data = fis.read()) != -1)
                out.print((char) data);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
