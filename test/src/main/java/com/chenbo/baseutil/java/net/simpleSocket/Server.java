package com.chenbo.baseutil.java.net.simpleSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : chenbo
 * @date : 2021-02-20
 */
public class Server {
    final static int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("server start");
            // 进行阻塞
            Socket socket = server.accept();
            // 新建一个线程处理客户端请求
            new Thread(new ServerHandler(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
