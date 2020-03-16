package org.dean.duck.netty.in.action.channels;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * channel执行顺序server端
 *
 * @author eric
 */
public class ChannelSeqServer {
	private static final int PORT = 7777;

	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup worker = new NioEventLoopGroup(2);

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boss, worker)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)  //设定队列大小
					.handler(new LoggingHandler())
					.childHandler(new UserChannelInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}

	}
}
