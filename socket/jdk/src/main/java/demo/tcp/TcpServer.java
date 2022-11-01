package demo.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : chenbo
 * @date : 2022-11-01
 */
public class TcpServer {
    public static void main(String[] args) {

        try (
                // 服务端套接字
                ServerSocket serverSocket = new ServerSocket(9999);
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                // 获取客户端输入流
                InputStream inputStream = socket.getInputStream();
                // 输出流
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            System.out.println("服务端收到消息：" + outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
