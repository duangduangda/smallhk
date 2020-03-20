package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class GroupChatServer {
	public static void main(String[] args) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(5);
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new GroupChatServerChannelInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
