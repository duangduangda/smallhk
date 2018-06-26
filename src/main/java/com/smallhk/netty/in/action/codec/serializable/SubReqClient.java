package com.smallhk.netty.in.action.codec.serializable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/18
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SubReqClient {
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (null != args && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        new SubReqClient().connect(port,"127.0.0.1");
    }

    private void connect(int port, String host) throws InterruptedException {
        // 配置客户端线程池组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            nioSocketChannel.pipeline().addLast(new ObjectEncoder());
                            nioSocketChannel.pipeline().addLast(new SubReqClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
