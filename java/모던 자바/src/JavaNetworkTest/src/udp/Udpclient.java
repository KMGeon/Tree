package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Udpclient {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 데이터가 저장될 공간으로 byte배열을 생성함
	
	public Udpclient() {
		msg = new byte[100];
		
		try {
			// 포트번호를 지정하지 않으면 이용가능한 임의의 포트번호 할당됨.
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		
		try {
			InetAddress serverAddr = InetAddress.getByName("192.168.142.3");
			
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp); // 패킷 전송
			
			dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp); // 패킷 수신
			
			// getData는 받은 데이터를 바이트로 받아준다.
			System.out.println("현재 서버 시간 => " + new String(dp.getData()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			ds.close(); // 소켓 종료.
		}
	}
	
	public static void main(String[] args) {
		new Udpclient().start();
	}
	
}
