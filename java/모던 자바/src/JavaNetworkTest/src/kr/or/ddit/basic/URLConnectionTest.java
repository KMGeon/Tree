package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		// URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상 클래스
		
		// 특정 서버(예 : naver서버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header정보 가져오기
		
		// URLConnection객체 구하기
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type: " + urlConn.getContentType());
		System.out.println("Encoding: " + urlConn.getContentEncoding());
		System.out.println("Content: " + urlConn.getContent());
		System.out.println();
		
		// 전체 Header 정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		// Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("-------------------------------------------------------------------------------------------------");
		
		// Content 가져오기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
		
		// 내용 출력하기
		String str = "";
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		
		br.close();
		
	}
}
