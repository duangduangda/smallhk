package org.dean.duck.netty.in.action.examples.simpletcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.dean.duck.netty.in.action.examples.simpletcp.v3.SimpleTcpServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author eric
 */
public class SimpleTcpServer {
	private static final Logger log = LoggerFactory.getLogger(SimpleTcpServer.class);

	public static void main(String[] args) {
		// group中如果不指定线程的数目，默认是实际CPU数 * 2
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(3);
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		try {
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.handler(new LoggingHandler())
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new SimpleTcpServerHandler());
						}
					});
			ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(7777)).sync();
			channelFuture.addListener(future -> {
				if (future.isSuccess()) {
					log.info("bind successfully~~~~~");
				} else {
					log.info("fail to bind");
				}
			}).channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}


	}
}
