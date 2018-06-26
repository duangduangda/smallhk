package com.smallhk.netty.in.action.codec.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

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
public class MessagePackEchoClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).option(ChannelOption.TCP_NODELAY,true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1024,0,2,0,2));
                            socketChannel.pipeline().addLast("messagePackDecoder",new MessagePackDecoder());
                            socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("messagePackEncoder",new MessagePackEncoder());
                            socketChannel.pipeline().addLast(new MessagePackEchoClientHandler(5));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
