package org.dean.duck.netty.in.action.examples.simpletcp.v2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 耗时任务：用户自定义任务,任务提交至taskQueue
 *
 * @author eric
 */
public class SimpleTcpServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(SimpleTcpServerHandler.class);

	/**
	 * @param ctx
	 * @param msg
	 *
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.channel().eventLoop().execute(() -> {
			try {
				Thread.sleep(10 * 1000);
				// 将msg转化成一个buf
				ByteBuf byteBuf = (ByteBuf) msg;
				log.info("from client:" + ctx.channel().remoteAddress() + " ,received msg:" + byteBuf.toString(CharsetUtil.UTF_8));
			} catch (Exception e) {
				log.error("发生异常：", e);
			}
		});

		ctx.channel().eventLoop().execute(() -> {
			try {
				Thread.sleep(20 * 1000);
				ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,client123", CharsetUtil.UTF_8));

			} catch (Exception e) {
				log.error("发生异常", e);
			}
		});
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,client", CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
