package org.dean.duck.netty.in.action.channels;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author eric
 */
public class UserChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new FirstChannelHandler());
		ch.pipeline().addLast(new SecondChannelHandler());
		ch.pipeline().addFirst(new ThirdChannelHandler());
	}
}
