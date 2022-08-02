package HighJava.src.JavaIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class T02FileTest {
    public static void main(String[] args) {
        File file1 = new File("E:/D_Others/sample.txt");
        File file2 = new File("E:/D_Others/test.txt");

        if (file1.exists()) { //exist는 파일이 존재하는지 확인
            System.out.println(file1.getAbsolutePath() + "은 존재합니다.");
        } else {
            System.out.println(file1 + "은 없는 파일입니다.");
        }
        try {
            if (file1.createNewFile()) {
                System.out.println(file1.getAbsolutePath() + "파일을" +
                        "새로 만들었습니다.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file2.exists()) {
            System.out.println(file2.getAbsolutePath() + "은 존재합니다.");
        } else {
            System.out.println(file2.getAbsolutePath() + "은 없는 파일입니다.");
        }
        System.out.println("---------------------------------------");

        File file3 = new File("E:/D_Others");
        File[] files = file3.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            System.out.print(file.getName() + "->");
            if (file.isFile()) {
                System.out.println("파일");
            } else if (file.isDirectory()) {
                System.out.println("디렉토리");
            }
        }
        System.out.println("----------------------------------------");
        String[] strFiles = file3.list(); //파일 이름만 출력
        for (String fName : Objects.requireNonNull(strFiles)) System.out.println(fName);
        System.out.println("--------------------------------------------------------------------");


        //===========================================================================================
        /*
        출력할 디렉토리 정보를 갖는 File 객체 생성하기
         */
        File file4 = new File("E:/D_Others");
        displayFileList(file4);
    }

    //지정된 디렉토리에 포함된 파일과 디렉토리 목록을 보여주는 메서드
    private static void displayFileList(File dir) {
        System.out.println("[" + dir.getAbsolutePath() + "]" + "디렉토리의 내용");

        //디렉토리 안의 모든 파일 목록 가져오기
        File[] files = dir.listFiles();
        //허위 디렉토리 정보를 저장할 arraylist생성( file배열의 인덱스값 저장)
       // List<Integer> subDirList = (List<Integer>) new ArrayList<>();
        List<Integer> subDirList = new ArrayList<>();
        //날짜를 출력하기 위한 포맷터 설정
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");

        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            String attr;
            String size = "";
            if (files[i].isDirectory()) {
                attr = "<DIR>";
                subDirList.add(i);//인덱스값을 List에 추가하기
            } else {
                size = files[i].length() + "";
                attr = files[i].canRead() ? "R" : " ";//읽기 가능한지
                attr += files[i].canWrite() ? "W" : " ";//쓰기 가능한지
                attr += files[i].isHidden() ? "H" : " ";//숨기기 가능한지?
            }
            System.out.printf("%s %5s %12s %s \n",
                    sdf.format(new Date(files[i].lastModified())), attr, size, files[i].getName()); //lastmodify 2022-08-02 오전 10:43
        }
        int dirCount = subDirList.size(); //하위폴더 수
        int fileCount = files.length - dirCount;
        System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
        System.out.println();
       // for(int i=0;i<subDirList.size();i++)
        for (Integer integer : subDirList) {
            // 하위 폴더의 내용들도 출력하기 위해 현재 메서드를 재귀호출하여 처리한다.
            displayFileList(files[integer]);
        }
    }
}
