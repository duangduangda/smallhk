package com.smallhk.core.io.nio;

import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Title. <br>
 * Description.selector的使用
 *              与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
 * <p>
 * Copyright: Copyright (c) 2018/2/28
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SelectorExample {
    public static void main(String[] args) throws IOException {
        try(
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("http://www.ifeng.com/", 80));
        ){
            //创建selector
            Selector selector = Selector.open();
            //与Selector一起使用时，Channel必须处于非阻塞模式下
            socketChannel.configureBlocking(false);
            //向selector注册channel,返回一个SelectionKey对象
//            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            while (true){
                //通过Selector选择通道
                int readyChannels = selector.select();
                if (readyChannels == 0){
                    continue;
                }
                // 返回就绪的通道列表
                Set selectionKeys = selector.selectedKeys();
                Iterator keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()){
                    SelectionKey selectionKey = (SelectionKey)keyIterator.next();
                    if (selectionKey.isAcceptable()){
                        System.out.println("a connection was accepted by a ServerSocketChannel");
                    }else if(selectionKey.isConnectable()){
                        System.out.println("a connection was established with a remote server");
                    }else if(selectionKey.isReadable()){
                        System.out.println("a channel is ready for reading");
                    }else if(selectionKey.isWritable()){
                        System.out.println("a channel is ready for writing\n");
                    }
                    //Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
                    // 下次该通道变成就绪时，Selector会再次将其放入已选择键集中
                    keyIterator.remove();
                }

            }
        }

    }
}
