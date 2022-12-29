package HighJava.src.JavaIO;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;

public class T14PrintStreamTest {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        FileOutputStream fos = new FileOutputStream("E:\\D_Others/print.txt");

        /*
            printStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 outputstream의 서브클래스이다.
         */
        PrintStream out = new PrintStream(fos);
        bw.write("안녕하세요 printStream입니다.1");
        out.print("안녕하세요 printstream입니다1.");
        out.println("안녕하세요 printstream입니다2.");
        out.println("안녕하세요 printstream입니다3.");
        out.println("안녕하세요 printstream입니다4.");
        out.println("안녕하세요 printstream입니다5.");
        out.println(out);
        out.println(3.14);

        /*
        printstream은 데이터를 문자로 출력하는 기능을 수행함(system.out)
        향상된 기능의 printwriter가 추가되었지만 계속 사용되고 있음

        printwriter가 printstream보다 다양한 언어의 문자를 처리하는데 적합하다.
         */

        FileOutputStream fos2 = new FileOutputStream("E:\\D_Others/print.txt");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));

        pw.print("안녕하세요 printwriter입니다.");
        pw.println("안녕하세요 printwriter입니다.2");
        pw.println("안녕하세요 printWriter입니다.3");

        pw.close();
    }
}
