package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class GroupChatClient {
	public static void main(String[] args) {
		NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.handler(new GroupChatChannelHandlerInitializer());
			ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 9999)).sync();
			Channel channel = channelFuture.channel();
			// 控制台发送消息
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNext()) {
				String msg = scanner.next();
				channel.writeAndFlush(msg);
			}

		} catch (Exception e) {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
