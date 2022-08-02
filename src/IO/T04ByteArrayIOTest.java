package HighJava.src.JavaIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.System.*;

public class T04ByteArrayIOTest {
    public static void main(String[] args) throws IOException {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        byte[] temp = new byte[4];//데이터를 읽을때 사용할 배열열

        //스트림 객체 생성하기
        ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len=0;//버퍼로 읽어드린 byte수

        //read() 메서드  => byte단위로 자료를 읽어와 int형으로 반환한다.
        //                  더 이상 읽어올 자료가 없으면 -1을 반환한다.
        //평소에 바이트로 리턴하지만 더 읽어올 자료가 없으면 -1로 반환을 하여서 return값을 int로 한다.
        while((len=bais.read(temp))!=-1)
        {
            out.println();
            out.println("temp->"+Arrays.toString(temp));
            out.println("len=>"+len);
            baos.write(temp,0,len);
            //write(byte[] b,int off , len) off 시작점 ,
        }
        /*
        이게 t03이랑 다른점은 03은 1바이트씩 처리를 하지만 이건 temp의 바이트만큼 처리한다.
        그래서 속도가 빠르자. 단점으로는 많은 메모리 할당으로 메모리에 과부화를 준다.
         */
        //출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
        byte[] outSrc = baos.toByteArray();

        System.out.println("insrc=>"+ Arrays.toString(inSrc));
        out.println("outSrc=>"+Arrays.toString(outSrc));
    }
}

