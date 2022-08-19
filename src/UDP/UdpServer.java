package kr.or.ddit.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
    private DatagramSocket ds;
    private DatagramPacket dp;
    private byte[] msg; //패킷 수신을 위한 바이트 배열 선언

    public UdpServer() {
        try {
            ds = new DatagramSocket(8888); //메시지를 수신을 위한 포트번호 설정
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        while (true) {
            //데이터를 수신하기 위한 패킷을 생성한다.
            msg = new byte[1];
            dp = new DatagramPacket(msg, msg.length);
            //수신을 위한 그릇을 설정
            System.out.println("패킷 수신 대기중...");

            //패킷을 통한 데이터를 수신한다.
            ds.receive(dp);//수신한 데이터를 담는다.
            System.out.println("패킷 수신 완료");

            //수신한 패킷으로 부터 client의 ip주소와 port정보를 얻는다.
            InetAddress address = dp.getAddress();
            int port = dp.getPort();

            //서버의 현재 시간을 시분초 형태 ([hh:mm:ss])로 반환한다.
            SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
            String time = sdf.format(new Date());
            msg = time.getBytes(); //시간문자열을 byte배열로 변환한다.
            //패킷을 생성해서 client에게 접속 (send)한다.
            dp = new DatagramPacket(msg, msg.length, address, port);
            ds.send(dp);

        }
    }

    public static void main(String[] args) throws IOException {
        new UdpServer().start();

    }
}
