package com.smallhk.netty.in.action.codec.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/26
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class MessagePackEchoServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).option(ChannelOption.SO_BACKLOG,1000)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler()).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1024,0,2,0,2));
                    socketChannel.pipeline().addLast("messagePackDecoder",new MessagePackDecoder());
                    socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
                    socketChannel.pipeline().addLast("messagePackEncoder",new MessagePackEncoder());
                    socketChannel.pipeline().addLast(new MessagePackEchoServerHandler());
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
