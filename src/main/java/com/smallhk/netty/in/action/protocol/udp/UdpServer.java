package com.smallhk.netty.in.action.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/29
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * Version: 1.0
 * <p>
 */
public class UdpServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true).handler(new UdpServerHandler());
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().await();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
