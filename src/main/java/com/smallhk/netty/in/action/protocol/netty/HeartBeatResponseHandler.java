package com.smallhk.netty.in.action.protocol.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/10/6
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class HeartBeatResponseHandler extends ChannelHandlerAdapter{

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatResponseHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        NettyMessage message = (NettyMessage) msg;
        if (null != message.getHeader() && message.getHeader().getType() == NettyMessage.MessageType.HEARTBEAT_REQ.value() ){
            logger.info("Received client heart beat message------>" + message);
            NettyMessage heartBeatResponse = buildHeartBeatResponse();
            logger.info("Send heart beat message to client-->" + heartBeatResponse);
            context.writeAndFlush(heartBeatResponse);
        }else{
            context.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeatResponse() {
        NettyMessage heartBeat = new NettyMessage();
        Header header = new Header();
        header.setType(NettyMessage.MessageType.HEARTBEAT_RESP.value());
        heartBeat.setHeader(header);
        return heartBeat;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause)throws Exception{
        cause.printStackTrace();
        if (null != context){
            context.fireExceptionCaught(cause);
        }
    }
}
