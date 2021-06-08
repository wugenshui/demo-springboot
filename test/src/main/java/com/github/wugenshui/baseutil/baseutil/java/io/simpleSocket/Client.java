package com.github.wugenshui.baseutil.baseutil.java.io.simpleSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author : chenbo
 * @date : 2021-02-20
 */
public class Client {
    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) {

        try (Socket socket = new Socket(ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            // 向服务端发送数据
            out.println("接受到服务器的请求数据...");
            String response = in.readLine();
            System.out.println("Client:" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
