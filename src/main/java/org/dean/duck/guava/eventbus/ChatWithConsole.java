package org.dean.duck.guava.eventbus;

import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: 通过socket连接，实现模拟聊天室：命令行输入一行，在服务端打印一行
 * @author: dean
 * @create: 2019/06/09 11:58
 */
public class ChatWithConsole {
    public static void main(String[] args) {
        EventBus channel = new EventBus();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                Socket connection = serverSocket.accept();
                EventListenerThread listener = new EventListenerThread(connection, channel);
                channel.register(listener);
                listener.start();
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
