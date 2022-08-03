package HighJava.src.JavaIO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.*;

/*
성능향상을 위한 보조스트림
(바이트 기반의 buffered 스트림 사용 예제)
 */
public class T11BufferedIOTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
//버퍼는 하드에 바로 쓰는거
        try {
            fos = new FileOutputStream("E:\\D_Others/bufferTest.txt");

            //버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8kb로 설정된다.

            //버퍼 크기가 5인 스트림 생성
            bos = new BufferedOutputStream(fos, 5);
            for (char ch = '1'; ch <= '9'; ch++) {
                bos.write(ch);
            }
            bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
            //close시 자동으로 호출된다.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
