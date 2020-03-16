package org.dean.duck.netty.in.action.examples.simpletcp.v1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一般用netty来发送和接收数据都会继承SimpleChannelInboundHandler和ChannelInboundHandlerAdapter这两个抽象类，那么这两个到底有什么区别呢？
 * <p>
 * 　　其实用这两个抽象类是有讲究的，在客户端的业务Handler继承的是SimpleChannelInboundHandler，而在服务器端继承的是ChannelInboundHandlerAdapter。
 * <p>
 * 　　最主要的区别就是SimpleChannelInboundHandler在接收到数据后会自动release掉数据占用的Bytebuffer资源(自动调用Bytebuffer.release())。而为何服务器端不能用呢，因为我们想让服务器把客户端请求的数据发送回去，而服务器端有可能在channelRead方法返回前还没有写完数据，因此不能让它自动release。
 *
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
		// 丢弃已接收的消息
		ReferenceCountUtil.release(msg);
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
