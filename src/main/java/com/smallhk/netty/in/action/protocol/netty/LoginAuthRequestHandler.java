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
public class LoginAuthRequestHandler extends ChannelHandlerAdapter{

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthRequestHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception{
        context.writeAndFlush(buildLoginAuthRequest());
    }

    private NettyMessage buildLoginAuthRequest() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(NettyMessage.MessageType.LOGIN_REQ.value());
        nettyMessage.setHeader(header);
        return nettyMessage;
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        NettyMessage message = (NettyMessage)msg;
        logger.info("client recevied reply from server:" + message.toString());

        if (null != message.getHeader() &&
                message.getHeader().getType() == NettyMessage.MessageType.LOGIN_RESP.value()){
            byte result = (byte)message.getBody();
            if (result == (byte) 0){
                logger.info("成功登录");
                context.fireChannelRead(msg);
            }else{
                logger.error("登录失败");
                context.close();
            }
        }else{
            context.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause) throws Exception{
        cause.printStackTrace();
        if (null != context){
            context.fireExceptionCaught(cause);
        }
    }


}
