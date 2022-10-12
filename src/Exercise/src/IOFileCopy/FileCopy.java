package IOFileCopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileCopy {
	public static void main(String[] args) {

		File file1 = new File("e:/D_Other/Tulips.jpg");
		File file2 = new File("e:/D_Other/복사본_Tulips.jpg");
		
		FileInputStream fin = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			fin = new FileInputStream(file1);
			fos = new FileOutputStream(file2);
			bis = new BufferedInputStream(fin);
			bos = new BufferedOutputStream(fos);
			
			int data;
			
//			long start = System.currentTimeMillis();
//			
//			while((data = fin.read()) != -1) {
//				
//				fos.write(data);
//				
//			}
//			long end = System.currentTimeMillis();
//			
//			System.out.println("그냥 소요시간 : " + (end-start));
			
			long bufferedStart = System.currentTimeMillis();
			
			while((data = bis.read()) != -1) {
				
				bos.write(data);
			}
			
			bos.flush();
			
			long bufferedEnd = System.currentTimeMillis();
			
			System.out.println("버퍼 소요시간 : " + (bufferedEnd-bufferedStart));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
			

		
		
	}
}
