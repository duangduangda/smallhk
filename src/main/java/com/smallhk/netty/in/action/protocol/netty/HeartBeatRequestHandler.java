package com.smallhk.netty.in.action.protocol.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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
public class HeartBeatRequestHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatRequestHandler.class);

    private volatile ScheduledFuture<?> heartbeat;

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception{
        NettyMessage message = (NettyMessage) msg;
        if (null != message.getHeader() &&
                message.getHeader().getType() == NettyMessage.MessageType.HEARTBEAT_RESP.value()) {
            logger.info("[" + new Date() + "]Client received heart beat message-->" + message);
        } else if (null != message.getHeader() &&
                message.getHeader().getType() == NettyMessage.MessageType.LOGIN_RESP.value()) {
            heartbeat = context.executor().scheduleAtFixedRate(new HeartBeatRequestHandler.HeartBeatTask(context)
                    , 0, 5000, TimeUnit.MILLISECONDS);
        } else {
            context.fireChannelRead(msg);
        }
    }


    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext channelHandlerContext;

        public HeartBeatTask(final ChannelHandlerContext context) {
            this.channelHandlerContext = context;
        }

        @Override
        public void run() {
            NettyMessage heartBeat = buildHeartBeat();
            logger.info("Client send heartbeat to server--->" + heartBeat);
            channelHandlerContext.writeAndFlush(heartBeat);
        }

        private NettyMessage buildHeartBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(NettyMessage.MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause)throws Exception{
        cause.printStackTrace();
        if (null != heartbeat){
            heartbeat.cancel(true);
            heartbeat = null;
        }

        context.fireExceptionCaught(cause);
    }
}
