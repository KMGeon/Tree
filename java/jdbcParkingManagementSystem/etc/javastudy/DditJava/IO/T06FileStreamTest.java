//package HighJava.src.JavaIO;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.stream.Stream;
//
//import static java.lang.System.*;
//
//
//public class T06FileStreamTest {
//    public static void main(String[] args) {
//        FileOutputStream fos = null;
//
//        try {
//            new FileOutputStream("E:\\D_Others/out.txt");
//            for (char ch = 'a'; ch < 'z'; ch++) {
//                fos.write();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        FileInputStream fis = null;
//        try {
//
//            FileInputStream fis = new FileInputStream("E:\\D_Others/out.txt");
//            int data = 0;
//            while((data==fis.read())!=-1){
//                out.print((char) );
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//}
