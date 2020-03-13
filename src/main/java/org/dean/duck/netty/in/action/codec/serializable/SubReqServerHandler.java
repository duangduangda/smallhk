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
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReq req = (SubscribeReq) msg;
		if ("eric".equalsIgnoreCase(req.getUsername())) {
			System.out.println("Service accept client subscibe req:["
					+ req.toString() + "]"
			);
			ctx.writeAndFlush(req.getSubReqID());
		}
	}

	private SubscribeResp resp(int subReqID) {
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqID(subReqID);
		resp.setRespCode(0);
		resp.setDesc("Netty book order succeed,3 days later,sent to the designated address");
		return resp;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		// 发送异常关闭链接
		context.close();
	}
}
