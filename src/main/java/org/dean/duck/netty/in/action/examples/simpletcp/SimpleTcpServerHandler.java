package org.dean.duck.netty.in.action.examples.simpletcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eric
 */
public class SimpleTcpServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(SimpleTcpServerHandler.class);

	/**
	 * @param ctx 上下文对象，含有pipeline,channel,地址
	 * @param msg 接收到的消息，默认是object
	 *
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 将msg转化成一个buf
		ByteBuf byteBuf = (ByteBuf) msg;
		log.info("from client:" + ctx.channel().remoteAddress() + " ,received msg:" + byteBuf.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// 将数据写入缓冲，并刷新
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,client", CharsetUtil.UTF_8));
	}

	/**
	 * 处理异常，关闭通道
	 *
	 * @param ctx
	 * @param cause
	 *
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}


}
