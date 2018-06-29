package com.smallhk.netty.in.action.protocol.udp;

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
public class UdpClientHander extends SimpleChannelInboundHandler<DatagramPacket>{
    @Override
    protected void messageReceived(ChannelHandlerContext context, DatagramPacket datagramPacket) throws Exception {
        String response = datagramPacket.content().toString(UTF_8);
        if (response.startsWith("结果：")){
            System.out.println(response);
            context.close();
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
