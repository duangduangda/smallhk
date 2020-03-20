package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eric
 */
public class HeartBeatCheckHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(HeartBeatCheckHandler.class);


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
		if (event instanceof IdleStateEvent) {
			String eventType = null;
			switch (((IdleStateEvent) event).state()) {
				case READER_IDLE:
					eventType = "读空闲";
					break;
				case WRITER_IDLE:
					eventType = "写空闲";
					break;
				case ALL_IDLE:
					eventType = "读写空闲";
					break;
			}
			log.info(ctx.channel().remoteAddress() + "超时--" + eventType);
//			System.out.println("服务器做处理");
			ctx.channel().close();
		}
	}
}
