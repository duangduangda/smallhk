package org.dean.duck.netty.in.action.channels;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eric
 */
public class ForthChannelHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(ForthChannelHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("This is the forth channel handler");
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,server", CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
