package org.dean.duck.netty.in.action.chapter9;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/2
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class DatagramChannelExample {
	public static void main(String[] args) {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new OioEventLoopGroup()).channel(OioSocketChannel.class).handler(new SimpleChannelInboundHandler<DatagramPacket>() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
				System.out.println("do something with the packet");

			}
		});
		ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(2048));
		channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
			if (!channelFuture1.isSuccess()) {
				System.out.println("channel bound!");
			} else {
				System.err.println("Bound failed!");
				channelFuture1.cause().printStackTrace();
			}
		});
	}
}
