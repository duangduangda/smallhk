package org.dean.duck.netty.in.action.examples.simplehttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author eric
 */
public class SimpleHttpServer {
	private static final Logger log = LoggerFactory.getLogger(SimpleHttpServer.class);

	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		try {
			serverBootstrap.group(boss, worker)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler())
					.childHandler(new SimpleHttpServerChannelInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8888)).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (
				Exception e) {
			log.error("服务端发生异常", e);
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}


	}

}
