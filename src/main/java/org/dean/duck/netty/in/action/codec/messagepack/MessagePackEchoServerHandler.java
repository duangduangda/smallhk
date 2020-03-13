package org.dean.duck.netty.in.action.codec.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/26
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class MessagePackEchoServerHandler extends ChannelHandlerAdapter {

	public void channelRead(ChannelHandlerContext context, Object msg) {
		System.out.println("Server received msg:" + msg.toString());
	}

	public void channelReadComplete(ChannelHandlerContext context) {
		context.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}
}
