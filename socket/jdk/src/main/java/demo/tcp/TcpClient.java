package demo.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : chenbo
 * @date : 2022-11-01
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        try (
                // 创建socket连接连接服务端
                Socket socket = new Socket("127.0.0.1", 9999);
                // 获取输出流
                OutputStream outputStream = socket.getOutputStream();
        ) {
            // 输出文本
            outputStream.write("hello world!".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
