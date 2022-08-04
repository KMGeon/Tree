package HighJava.src.JavaIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class T15ObjectStreamTest {
    public static void main(String[] args) {
        String File = "E:\\D_Others/memObj.bin";
        //member 객체 생성하기
        Member mem1 = new Member("유재석", 20, "강남");
        Member mem2 = new Member("하하", 30, "광교");
        Member mem3 = new Member("김종국", 10, "강서");
        Member mem4 = new Member("송지효", 40, "강북");

        ObjectOutputStream o1s = null;

        try {
//            객페를 파일에 저장하고
            //출력용 스트림 객체 생성하기
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(File)));

            //출력용 스트림 객체 생성하기

            //쓰기작업
            oos.writeObject(mem1);//직렬화
            oos.writeObject(mem2);
            oos.writeObject(mem3);
            oos.writeObject(mem4);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                o1s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(File)));

            Object obj = null;
            while ((obj = ois.readObject()) != null) { //역직렬화
                //파일의 마지막에 다다르면 eof exception발생

                //읽어온 객체를 원래의 타입으로 변환 후 사용한다.
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}

//Serializable : 직렬화 :  자바 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용 가능하도록
//바이트 형태로 데이터를 변환하는 기술

//Serializable 인터페이스를 구현하면 JVM에서 해당 객체는 저장하거나 다른 서버로 전송할 수 있도록 해준다.
class Member implements Serializable {
    //자바는 serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음
    /*
        trasient -> 직렬화가 되지 않을 멤버변수에 지정한다.(static필드도 직렬화가 되지 않는다)
        직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.

        직렬화가 되지 않는 멤버변수는 기본값을 저장된다.
        (참조형변수 : null ,  숫자형 변수: 0)

        transient를 붙이면 직렬화 대상이 되지 않고 직렬화 되는 시점에 디폴트값으로 된다.

        transient는 중요한 정보를 저장할 때 (ex) 주민등록번호
     */
    transient private String name;
    private int age;
    private String addr;

    public Member(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }
}
