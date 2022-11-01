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
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            // 创建socket连接连接服务端
            socket = new Socket("127.0.0.1", 9999);
            // 获取输出流
            outputStream = socket.getOutputStream();
            // 输出文本
            outputStream.write("hello world!".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
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
        }
    }
}
