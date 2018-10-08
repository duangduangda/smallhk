package com.smallhk.netty.in.action.protocol.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public class LoginAuthResponseHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthResponseHandler.class);

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whiteList = {"127.0.0.1"};

    private NettyMessage buildLoginRespnse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(NettyMessage.MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        NettyMessage message = (NettyMessage)msg;
        logger.info("server received from client:" + message);
        if (message.getHeader() != null
                && message.getHeader().getType() == NettyMessage.MessageType.LOGIN_REQ.value()
                ) {
            String nodeIndex = context.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildLoginRespnse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) context.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOk = false;
                for (String wip : whiteList) {
                    if (wip.equals(ip)) {
                        isOk = true;
                        break;
                    }
                }
                loginResp = isOk ? buildLoginRespnse((byte) 0) : buildLoginRespnse((byte) -1);
                if (isOk) {
                    nodeCheck.put(nodeIndex, true);
                }
            }
            logger.info("The login response is :" + loginResp + " body ["
                    + loginResp.getBody() + "]");
            context.writeAndFlush(loginResp);
        } else {
            context.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception{
        cause.printStackTrace();
        nodeCheck.remove(context.channel().remoteAddress().toString());
        if (null != context) {
            context.fireExceptionCaught(cause);
        }
    }
}
