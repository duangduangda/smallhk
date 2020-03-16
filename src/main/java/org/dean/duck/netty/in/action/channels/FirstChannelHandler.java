package org.dean.duck.netty.in.action.channels;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstChannelHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(FirstChannelHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("This is the first channel handler.");
		ctx.fireChannelRead(msg);
	}
}
