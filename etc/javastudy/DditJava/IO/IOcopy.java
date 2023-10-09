package HighJava.src;

import java.io.*;

public class IOcopy {
    public static void main(String[] args) {


        String oldFile = "E:\\D_Others/Tulips.jpg";

        String copyFilePath = "E:\\D_Others/Tulips3.jpg";


        File copyFile = new File(copyFilePath);

        try {

            FileInputStream fis = new FileInputStream(oldFile);
            FileOutputStream fos = new FileOutputStream(copyFile);

            int fileByte;

            while ((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }

            fis.close();
            fos.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}



