package org.dean.duck.netty.in.action.codec.serializable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/18
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

	public SubReqClientHandler() {
	}

	@Override
	public void channelActive(ChannelHandlerContext context) {
		for (int i = 0; i < 10; i++) {
			context.write(subReq(i));
		}
		context.flush();
	}

	private SubscribeReq subReq(int i) {
		SubscribeReq req = new SubscribeReq();
		req.setAddress("北京市海淀区");
		req.setProductName("netty in action");
		req.setUsername("eric");
		req.setSubReqID(i);
		return req;
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		System.out.println("receive server response:" + msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext context) {
		context.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}
}
