package org.dean.duck.netty.in.action.chapter9;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Title. <br>
 * Description. 使用ServerBootrap引导服务器
 * <p>
 * Copyright: Copyright (c) 2018/4/28
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class BootstrapingServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new SimpleHandler());

        ChannelFuture channelFuture = serverBootstrap.bind(2048);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    System.out.println("Server bound");
                }else{
                    System.err.println("bound fail");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
