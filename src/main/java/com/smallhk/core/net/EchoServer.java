package com.smallhk.core.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/29
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        //表示服务器进程占用了8000
        serverSocket = new ServerSocket(port);
        System.out.println("server start");
    }

    public String echo(String msg){
        return "echo:" + msg;

    }

    public void service() throws IOException {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("new client connection from " + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                String msg = null;
                while ((msg = bufferedReader.readLine()) != null) {
                    System.out.println(msg);
                    printWriter.println(echo(msg));
                    if ("bye".equals(msg)) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    socket.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer().service();
    }

}
