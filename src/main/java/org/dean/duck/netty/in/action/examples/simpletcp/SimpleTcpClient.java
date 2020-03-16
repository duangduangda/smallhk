package org.dean.duck.netty.in.action.examples.simpletcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.dean.duck.netty.in.action.examples.simpletcp.v1.SimpleTcpClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author eric
 */
public class SimpleTcpClient {
	private static final Logger log = LoggerFactory.getLogger(SimpleTcpClient.class);

	public static void main(String[] args) {
		NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(nioEventLoopGroup)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new SimpleTcpClientHandler());
						}
					});

			ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 7777)).sync();
			channelFuture.addListener(future -> {
				log.info("连接成功~~~");
			}).channel().closeFuture().sync();
		} catch (Exception e) {
			nioEventLoopGroup.shutdownGracefully();
		}

	}
}
