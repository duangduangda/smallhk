package com.smallhk.netty.in.action.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @author Administrator
 * <p>
 * Version: 1.0
 * <p>
 */
public class EchoClient {

    private String host;

    private int port;

    public EchoClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost",65535).start();
    }

    private void start() throws Exception {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            // 引导启动客户端
            Bootstrap bootstrap = new Bootstrap();
            // 设置事件执行器（线程池），指定通道类型，设置连接的服务器地址，设置业务处理器，
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully().sync();
        }
    }
}
