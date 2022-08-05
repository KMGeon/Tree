package HighJava.src.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import static java.lang.System.*;

/*
외부의 properties파일을 읽어와 properties 객체로 처리하기
 */
public class T99PropertiesTest {
    public static void main(String[] args) {
        //읽어온 정보를 저장할 객체 생성
        final Properties prop = new Properties();

        //읽어올 파일명을 이용한 file객체 생성
        final File file = new File("res/db.properties");

        try {
            //파일 읽기를 수행할 fileinpustream객체 생성
            final FileInputStream fis = new FileInputStream(file);

            //properties객체로 파일 내용 읽기
            prop.load(fis);
            //파일 내용을 읽어와 키와 값으로 분류한 후 프로퍼티 객체에 담아준다.

            //읽어온 자료 출력하기

            //속성값을 접근하기 위한 enumeration객체 가져오기
            Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();

            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String value = prop.getProperty(key);
                out.println(key + "->" + value);
            }
            out.println("출력 끝...");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
