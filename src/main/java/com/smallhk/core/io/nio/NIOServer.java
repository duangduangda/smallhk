package com.smallhk.core.io.nio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/9
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("nio_server").build();
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 3, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1024), threadFactory);
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(80));
            // 获取选择器
            final Selector selector = Selector.open();
            // 注册监听事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                final int num = selector.select(1000L);
                if (num == 0) {
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        final SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        selectionKey.cancel();
                        socketChannel.configureBlocking(false);
                        threadPoolExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ByteBuffer request = ByteBuffer.allocate(1024);
                                    socketChannel.read(request);
                                    request.flip();
                                    System.out.println("当前线程数" + threadPoolExecutor.getActiveCount() + ",收到请求：" + new String(request.array()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }finally {
                                    try {
                                        socketChannel.register(selector,SelectionKey.OP_READ);
                                    } catch (ClosedChannelException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        });
                    }
                }
                selectionKeyIterator.remove();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocketChannel) {
                serverSocketChannel.close();
            }
        }

    }

}
