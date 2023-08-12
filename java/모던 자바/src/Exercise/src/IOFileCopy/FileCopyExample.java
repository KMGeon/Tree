package IOFileCopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExample {
	public static void main(String[] args) {
		
		File inputFile = new File("d:/D_Other/Tulips.jpg");
		File outputFile = new File("d:/D_Other/복사본_Tulips.jpg");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			bis = new BufferedInputStream(fis, (int) inputFile.length());
			bos = new BufferedOutputStream(fos, (int) inputFile.length());
			
			int data = 0;
			long start = System.currentTimeMillis();
			
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
			
			bos.flush();
			
			System.out.println("복사완료");
			System.out.println("소요 시간(ms): " + (System.currentTimeMillis() - start));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
	}
}
