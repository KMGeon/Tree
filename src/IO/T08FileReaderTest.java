package HighJava.src.JavaIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
문자기반 스트림 예제
 */
public class T08FileReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("E:/D_Others/testChar.txt");
            int data =0;
            while((data=fr.read())!=-1){
                System.out.print((char) data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fr.close();
        }
    }
}
