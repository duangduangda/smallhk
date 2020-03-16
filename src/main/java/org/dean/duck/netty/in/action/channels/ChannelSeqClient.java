package org.dean.duck.netty.in.action.channels;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author eric
 */
public class ChannelSeqClient {
	public static void main(String[] args) {
		NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ThirdChannelHandler());
							ch.pipeline().addLast(new ForthChannelHandler());
						}
					});
			ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 7777)).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			eventLoopGroup.shutdownGracefully();
		}
	}

}
