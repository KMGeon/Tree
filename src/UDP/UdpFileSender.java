package kr.or.ddit.UDP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * The type Udp file sender.
 */
public class UdpFileSender {
    //    datagramPacket : 애플리케이션에서 주고 받을 데이터와 관련된 클래스
//    datagramsocket : 실제 데이터의 전송을 책임
    private DatagramSocket ds;
    private DatagramPacket dp;

    private InetAddress receiveAddr;
    private int port; // 데이터 보낼때 사용하는 포트번호
    private File file;

    /*
     * Instantiates a new Udp file sender.
     *
     * @param receiveIp the receive ip
     * @param port      the port
     */
    public UdpFileSender(String receiveIp, int port) {
        try {
            ds = new DatagramSocket();

            this.port = port;
            receiveAddr = InetAddress.getByName(receiveIp);
            file = new File("C:\\xampp\\htdocs\\jsstudy\\img\1.jpg");


            if (!file.exists()) {
                System.out.println("파일이 존재하지 않습니다.");
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     * Start.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void start() throws InterruptedException {

        long fileSize = file.length();
        long totalReadBytes = 0;

        long startTime = System.currentTimeMillis();
        try {

            sendData("start".getBytes()); //전송시작을 알려주기 위한 문자열 전송
            //파일명 전송
            sendData(file.getName().getBytes());

            //총 파일 사이즈 정보 전송하기
            sendData(String.valueOf(fileSize).getBytes());

            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[1000];
            while (true) {
                //패킷전송간의 간격을 주기 위해서...
                Thread.sleep(10);

                int readBytes = fis.read(buffer, 0, buffer.length);
                if (readBytes == -1) {
                    break;
                }
                sendData(buffer, readBytes); // 읽어온 파일데이터 전송하기
                totalReadBytes += readBytes;
                System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + "Byte(s)(" + (totalReadBytes * 100 / fileSize) + "%");
            }
            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;
            double transferSpeed = fileSize / diffTime;

            System.out.println("걸린 시간: " + diffTime + "(ms)");
            System.out.println("평균 전송속도: " + transferSpeed + "bytes/ms");
            System.out.println("전송 완료...");

            fis.close();
            ds.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    바이트 배열 데이터 전송하기
    bytes 전송할 바이트 배열

    data 보낼 바이트 배열 데이터
    length 보낼 바이트 데이터 크기

     */

    private void sendData(byte[] bytes) throws IOException {
        sendData(bytes, bytes.length);
    }


    private void sendData(byte[] data, int length) throws IOException {
        dp = new DatagramPacket(data, length, receiveAddr, port);
        ds.send(dp);
    }

    public static void main(String[] args) throws InterruptedException {
        new UdpFileSender("192.168.142.13", 8888).start();
    }
}
