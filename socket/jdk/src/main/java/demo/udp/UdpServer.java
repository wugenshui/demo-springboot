package demo.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author : chenbo
 * @date : 2022-11-01
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        // 开放端口
        DatagramSocket socket = new DatagramSocket(9090);
        // 接收数据
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        // 阻塞接受
        socket.receive(packet);

        System.out.println(packet.getAddress().getHostAddress() + ":" + new String(packet.getData(), 0, packet.getLength()));

        //关闭连接
        socket.close();
    }
}
