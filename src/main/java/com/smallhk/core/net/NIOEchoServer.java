package com.smallhk.core.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/5
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NIOEchoServer {
    public static void main(String[] args) {
        try(
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                ) {
            ServerSocket serverSocket = serverSocketChannel.socket();
            // 绑定要监听的端口
            serverSocket.bind(new InetSocketAddress(80));
            serverSocketChannel.configureBlocking(false);
            // 注册感兴趣的事件
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
