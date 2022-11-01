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
        // 服务端套接字
        ServerSocket serverSocket = null;
        // 客户端套接字
        Socket socket = null;
        // 输入流
        InputStream inputStream = null;
        // 输出流
        ByteArrayOutputStream outputStream = null;
        try {
            // 监听端口
            serverSocket = new ServerSocket(9999);
            // 等待客户端连接
            socket = serverSocket.accept();
            // 获取客户端输入流
            inputStream = socket.getInputStream();
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            System.out.println("服务端收到消息：" + outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
