package org.dean.duck.netty.in.action.examples.simplehttp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.dean.duck.netty.in.action.examples.simplehttp.handler.SimpleHttpServerHandler;

/**
 * 自定义管道，用于管理handler
 *
 * @author eric
 */
public class SimpleHttpServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		// 设置编解码器
		pipeline.addLast(new HttpServerCodec());
		// 增加自定义的处理器
		pipeline.addLast(new SimpleHttpServerHandler());

	}
}
