package demo.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author : chenbo
 * @date : 2022-11-01
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        //1 建立一个socket
        DatagramSocket socket = new DatagramSocket();
        //2 建立一个包
        String msg = "hello world!";
        // 发送给谁
        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9090;
        //数据，数据长度，发给谁
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);
        //3 发送包
        socket.send(packet);
        //关闭流
        socket.close();
    }
}
