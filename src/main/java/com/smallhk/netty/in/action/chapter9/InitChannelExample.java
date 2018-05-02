package com.smallhk.netty.in.action.chapter9;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * Title. <br>
 * Description. mutil handler
 * <p>
 * Copyright: Copyright (c) 2018/5/2
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class InitChannelExample {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitImpl());
        ChannelFuture channelFuture = serverBootstrap.bind(2048).sync();
        channelFuture.channel().closeFuture().sync();
    }

    static final class ChannelInitImpl extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new HttpClientCodec()).addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }
}
