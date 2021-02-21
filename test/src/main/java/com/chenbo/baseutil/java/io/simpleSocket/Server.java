package com.chenbo.baseutil.java.io.simpleSocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : chenbo
 * @date : 2021-02-20
 */
public class Server {
    final static int PORT = 8765;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("server start");
            // 进行阻塞
            Socket socket = server.accept();
            // 新建一个线程处理客户端请求
            new Thread(new ServerHandler(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
