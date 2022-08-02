package HighJava.src.JavaIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
    public static void main(String[] args) {
        byte[]inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc;
        //in복사해서 out으로 보내기

        /* 직접 복사하기
        byte[] outSrc = new byte[inSrc.length];
        System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
        System.out.println("직접 복사 후 outSrc->"+ Arrays.toString(outSrc));*/

        /* ARRAYCOPY를 이용한 배열 복사방법
        byte[] outSrc = new byte[inSrc.length];
        System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);

        System.out.println("직접 복사 후 outSrc->"+ Arrays.toString(outSrc));*/


        //스트림 객체 생성하기
        ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int data; //읽어온 바이트 데이터를 저장할 변수

        //read() 메서드  => byte단위로 자료를 읽어와 int형으로 반환한다.
        //                  더 이상 읽어올 자료가 없으면 -1을 반환한다.
        //평소에 바이트로 리턴하지만 더 읽어올 자료가 없으면 -1로 반환을 하여서 return값을 int로 한다.
        while((data=bais.read())!=-1){
            baos.write(data);
        }
        //출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
        outSrc = baos.toByteArray();

        System.out.println("insrc=>"+Arrays.toString(inSrc));
        System.out.println("outSrc=>"+Arrays.toString(outSrc));
    }
}
