package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eric
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger log = LoggerFactory.getLogger(GroupChatClientHandler.class);

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info(msg.trim());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
