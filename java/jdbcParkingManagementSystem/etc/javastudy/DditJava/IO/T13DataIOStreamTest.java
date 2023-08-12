package HighJava.src.JavaIO;

import java.io.*;

public class T13DataIOStreamTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream("E:\\D_Others/test.dat");
            //DataOutputStream을 출력용 데이터를 자료형에 맞게 출력해 준다.
            dos = new DataOutputStream(fos);
            dos.writeUTF("홍길동"); //문자열 데이터 출력(UTF-8)
            dos.writeInt(17);       //정수형으로 데이터 출력
            dos.writeFloat(3.14F);  //실수형으로 데이터 출력(Float)
            dos.writeDouble(3.14);       //실수형으로 데이터 출력(double)
            dos.writeBoolean(true);       //논리형으로 데이터 출력

            System.out.println("출력 완료..");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //출력한 자료 읽어오기
//            FileInputStream fis = null;
//            DataInputStream dis = null;

            try (FileInputStream fis = new FileInputStream("E:\\D_Others/test.dat");
                 DataInputStream dis = new DataInputStream(fis);) {
                System.out.println("문자열 자료" + dis.readUTF());
                System.out.println("정수형 자료" + dis.readInt());
                System.out.println("실수형 자료" + dis.readFloat());
                System.out.println("실수형 자료" + dis.readDouble());
                System.out.println("논리형 자료:" + dis.readBoolean());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
