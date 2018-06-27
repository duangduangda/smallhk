package com.smallhk.netty.in.action.protocol.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/27
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class WebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).option(ChannelOption.SO_BACKLOG,1024).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler()).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 将请求和应答消息编码或者解码为http消息
                    socketChannel.pipeline().addLast("http-codec",new HttpServerCodec());
                    // 将http消息的多个部分组合成一条完整的Http消息
                    socketChannel.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
                    // 向客户端发送html5文件，主要用于支持浏览器和服务端进行websocket通信
                    socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                    socketChannel.pipeline().addLast("handler",new WebSocketServerHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
