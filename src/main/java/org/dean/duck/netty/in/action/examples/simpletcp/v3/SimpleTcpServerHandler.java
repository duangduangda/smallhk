package org.dean.duck.netty.in.action.examples.simpletcp.v3;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 用户自定义定时任务,任务提交至scheduleTaskQueue
 *
 * @author eric
 */
public class SimpleTcpServerHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SimpleTcpServerHandler.class);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("Hello,client.Server has read all information.");
	}

	/**
	 * 自定义定时任务，延迟5秒钟执行，每隔3秒执行一次
	 *
	 * @param ctx
	 * @param msg
	 *
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.channel().eventLoop().scheduleAtFixedRate(() -> {
			try {
				Thread.sleep(5 * 1000);
				ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,client.Send new time string:" + System.currentTimeMillis(), CharsetUtil.UTF_8));
			} catch (Exception e) {
				log.error("发生异常", e);
			}
		}, 5, 3, TimeUnit.SECONDS);
	}
}
