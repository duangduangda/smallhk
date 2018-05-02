package com.smallhk.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/28
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class ServerSocketChannelExample {
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress(80));
            serverSocketChannel.configureBlocking(false);
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null){
                    System.out.println("do something with socketChannel...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
