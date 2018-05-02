package com.smallhk.core.net;

import sun.beans.editors.ByteEditor;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

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
public class NIOEchoClient {
    public static void main(String[] args) {
        try(SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",80));
            Selector selector = Selector.open();
            // 向channel注册selector以及感兴趣的事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // 如果nkeys大于0，表示有感兴趣的事件发生
            int nKeys = selector.select();
            SelectionKey key = null;
            if (nKeys > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey:selectionKeys){
                    if (selectionKey.isConnectable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        channel.configureBlocking(false);
                        key = channel.register(selector, SelectionKey.OP_READ);
                        channel.finishConnect();
                    }else if (selectionKey.isReadable()){
                        // 有流可读
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        try {
                            int readBytes = 0;
                            try{
                                 int ret = 0;
                                 while ((ret = channel.read(byteBuffer)) > 0) {
                                     readBytes += ret;
                                 }
                            }finally {
                                byteBuffer.flip();
                            }
                        }finally {
                            if (null != byteBuffer){
                                byteBuffer.clear();
                            }
                        }
                    }else if (selectionKey.isWritable()){
                        // 可写入流
//                        SocketChannel channel = (SocketChannel) key.channel();
//                        int writesize = channel.write(ByteBuffer);
//                        if (writesize == 0){
//                            key.interestOps(selectionKey.interestOps() & (SelectionKey.OP_WRITE));
//                        }
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
