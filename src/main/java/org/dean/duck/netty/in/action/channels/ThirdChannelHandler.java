package org.dean.duck.netty.in.action.channels;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eric
 */
public class ThirdChannelHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(ThirdChannelHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("This is the third channel handler");
		// 转发给下一个ChannelHandler
		ctx.fireChannelRead(msg);
	}
}
