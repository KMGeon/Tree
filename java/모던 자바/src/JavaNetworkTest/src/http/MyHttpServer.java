package http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/*
 * 간단한 웹서버 예제
 * @author pc17
 * */
public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	// http서버 시작...
	public void start() {
		
		System.out.println("HTTP 서버가 시작되었습니다.");
		
		try(ServerSocket server = new ServerSocket(this.port)) {
			
			while(true) {
				
				try {
					
					Socket socket = server.accept();
					
					HttpHandler handler = new HttpHandler(socket);
					
					new Thread(handler).start();
					
				} catch (IOException e) {
					System.out.println("커넥션 오류!!!");
					e.printStackTrace();
				}catch (RuntimeException e) {
					System.out.println("알수 없는 오류!!!");
					e.printStackTrace();
				}
				
			}
			
			
		} catch (IOException e) {
			System.out.println("서버 시작 오류!!!");
			e.printStackTrace();
		}
			
	}
	
	
	/*
	 * HTTP 요청 처리를 위한 Runnable 클래스
	 * */
	private class HttpHandler implements Runnable {
		
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();
				
				// Request Line 
				String reqLine = br.readLine(); // 첫줄은 요청라인...
				
				printMsg("request Line : ", reqLine);
				
				while(true) {
					String str = br.readLine();
					if(str.equals("")) break;
					
					sb.append(str + "\n");
				}
				
				// Header 정보
				String reqHeaderStr = sb.toString();
				
				printMsg("요청헤더: ", reqHeaderStr);
				
				String reqPath = ""; // 요청 경로
				
				// 요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(reqLine);
				
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					
					if(token.startsWith("/")) {
						reqPath = token;
						
						break;
					}
				}
				
				// URL 디코딩 처리(한글 깨짐 문제 처리용)(지금의 URL은 UTF-8로 인코딩해서 넘어온다.)
				reqPath = URLDecoder.decode(reqPath, encoding);
				
				String filePath = "./WebContent/" + reqPath;
				
				// 해당 파일이름을 이용하여 Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
				
				// CSS 파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css"; // 마임파일 형태
				}
				
				File file = new File(filePath);
				
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				// 요청헤더가 HTTP/1.0이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				if(reqLine.indexOf("HTTP/") != -1) {
					out.write(header);
				}
				
				// 응답내용 보내기 전 반드시 Empty Line 보내야 한다.
				out.write("\r\n\r\n".getBytes());
				
				out.write(body); // 응답 내용 보내기
				
				out.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
		}

	}
	
	/*
	 * 메시지 출력 메소드
	 * @param title
	 * @param msg
	 * */
	private void printMsg(String title, String msg) {
		System.out.println("========================================================");
		System.out.println(title);
		System.out.println("========================================================");
		System.out.println(msg);
		System.out.println("--------------------------------------------------------");
	}
	
	/*
	 * 응답 헤더 생성하기
	 * @param contentLength 응답내용 크기
	 * @param memeType 마임타입
	 * @return 바이트 배열
	 * */
	private byte[] makeResponseHeader(int contentLength, String memeType) {
		// 네트워크의 layer에서는  대부분 \r\n이 줄바꿈이다.
		String header = "HTTP/1.1 200 OK\r\n"
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length: " + contentLength + "\r\n"
				+ "Content-type: " + memeType + "; charset=" + this.encoding;
		
		return header.getBytes();
	}
	
	/*
	 * 응답내용 생성하기
	 * @param filePath 응답으로 사용할 파일경로
	 * @return 바이트 배열 데이터
	 * */
	private byte[] makeResponseBody(String filePath) {
		
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			// 파일의 바이트만큼의 버퍼를 만들어 코드진행
			File file = new File(filePath);
			data = new byte[(int) file.length()]; // file.length()는 바이트 수가 넘어온다.
			
			fis = new FileInputStream(file);
			fis.read(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
		
	}
	
	/**
	 * 에러페이지 생성
	 * @param out
	 * @param statusCode
	 * @param errMsg
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;  
		
		try {
			
			out.write(statusLine.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
}
