package com.smallhk.netty.in.action.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class EchoServer {
    private int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void  start()throws Exception{
        //创建NioEventLoopGroup对象来处理时间，如接受新练级诶，接收数据，写数据等
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            // 创建ServerBootStrap来引导绑定和启动服务器
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            //group指定事件处理对象，channel指定通道类型，localAddress设置服务器监听端口，childHandler设置执行所有的连接请求处理器
            serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new EchoServerHandler());
                }
            });
            //  异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture();
        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(65535).start();
    }

}
