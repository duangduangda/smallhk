package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author eric
 */
public class GroupChatServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new StringEncoder());
		ch.pipeline().addLast(new StringDecoder());
		ch.pipeline().addLast(new GroupChatServerHandler());
		// readIdle表示多长时间没有读，就会发出一个心跳检测包检测是否连接，writeIdle表示多长时间没有写，allIdle表示多长时间没有读写
		ch.pipeline().addLast(new IdleStateHandler(3, 5, 7));
		// 自定义心跳处理器
		ch.pipeline().addLast(new HeartBeatCheckHandler());


	}
}
