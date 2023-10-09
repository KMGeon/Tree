package kr.or.ddit.UDP;

import com.sun.tools.javac.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//udp는 receiver가

/**
 * The type Udp file receiver.
 */
public class UdpFileReceiver {
    private DatagramSocket ds;
    private DatagramPacket dp;

    private byte[] buffer;


    /**
     * Instantiates a new Udp file receiver.
     *
     * @param port the port
     */
    public UdpFileReceiver(int port) {
        try {
            ds = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }


    private byte[] receiveData() throws IOException {
        buffer = new byte[1000];
        dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        return dp.getData();
    }

    /**
     * Start.
     *
     * @throws IOException the io exception
     */
    public void start() throws IOException {
        long fileSize = 0;
        long totalReadBytes = 0;
        int readBytes = 0;
        System.out.println("파일 수신 대기 중...");
        String str = new String(receiveData()).trim();

        if (str.equals("start")) { //sender에서 전송을 시작한 경우...
            //파일명 받기
            str = new String(receiveData()).trim();
            FileOutputStream fos =
                    new FileOutputStream("E:\\D_Other" + str);

            //전송 파일 크기(bytes) 받기
            str = new String(receiveData()).trim();
            fileSize = Long.parseLong(str);

//            위에가 받은 파일 설정

            long startTime = System.currentTimeMillis();

            while (true) {
//                1000바이트 날라오다가 1000바이트가 아니면 getlength로 파악
                byte[] data = receiveData();
                readBytes = dp.getLength();

                fos.write(data, 0, readBytes);

                totalReadBytes += readBytes;

//                총 진행 상태를 말해준다.
                System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + "Byte(s)(" + (totalReadBytes * 100 / fileSize) + "%");
                if (totalReadBytes >= fileSize) {
                    break;
                }
            }

            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;
            double transferSpeed = fileSize / diffTime;

            System.out.println("걸린 시간: " + diffTime + "(ms)");
            System.out.println("평균 수신속도: " + transferSpeed + "bytes/ms");
            System.out.println("수신 완료...");

            fos.close();
            ds.close();
        } else {
            System.out.println("비정상 데이터 발견!!!");
            ds.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new UdpFileReceiver(8888).start();
    }
}
