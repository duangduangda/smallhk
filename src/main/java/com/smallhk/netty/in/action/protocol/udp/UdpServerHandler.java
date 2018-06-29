package com.smallhk.netty.in.action.protocol.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/29
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket msg) throws Exception {
        String request = msg.content().toString(UTF_8);
        System.out.println(request);

        if ("hello".equals(request)){
            channelHandlerContext.writeAndFlush(
                    new DatagramPacket(Unpooled.copiedBuffer("结果：" + System.currentTimeMillis(),UTF_8),msg.sender()));
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        if (context.channel().isActive()){
            context.close();
        }
    }
}
