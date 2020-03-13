package org.dean.duck.netty.in.action.chapter9;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Title. <br> Description.
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
public class BootstrapingClient {
	public static void main(String[] args) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
				System.out.println("Received data..");
				msg.clear();
			}

		});

		ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 2048);
		channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
			if (channelFuture1.isSuccess()) {
				System.out.println("Connection finished");
			} else {
				System.err.println("Connection failed");
				channelFuture1.cause().printStackTrace();
			}
		});
	}
}
