package com.smallhk.netty.in.action.protocol.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpHeaderUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaderUtil.setContentLength;


/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/27
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws Exception{
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {
        // 传统的http接入
        if (msg instanceof FullHttpRequest){
            handleHttpRequest(context,(FullHttpRequest)msg);
        }else if(msg instanceof WebSocketFrame){
            // websocket接入
            handleWebSocketFrame(context,(WebSocketFrame)msg);
        }

    }

    /**
     * 处理Websocket协议请求
     * @param context
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext context, WebSocketFrame frame) {
        // 判断是否关闭链路的命令，如果是，则关闭channel
        if (frame instanceof CloseWebSocketFrame){
            handshaker.close(context.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息,如果是，返回pong消息
        if (frame instanceof PingWebSocketFrame){
            context.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported",frame.getClass().getName()));
        }

        // 返回应答消息
        String request = ((TextWebSocketFrame)frame).text();
        if (logger.isInfoEnabled()){
            logger.info(String.format("%s received %s",context.channel(),request));
        }

        context.channel().write(new TextWebSocketFrame(request + ",欢迎使用Netty WebSocket"));
    }

    /**
     * 发送回应消息
     * @param context
     * @param request
     * @param response
     */
    public static void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest request, DefaultFullHttpResponse response){
        // 返回应答至客户端
        if (response.status().code() != HttpResponseStatus.OK.code()){
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            setContentLength(response,response.content().readableBytes());
        }

        // 如果非keep-alive，关闭连接
        ChannelFuture channelFuture = context.channel().writeAndFlush(response);
        if (!isKeepAlive(request) || response.status().code() != HttpResponseStatus.OK.code()){
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理http请求
     * @param context
     * @param request
     */
    private void handleHttpRequest(ChannelHandlerContext context, FullHttpRequest request) {
        // 如果http解码失败，返回http异常
        if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))){
            sendHttpResponse(context,request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        // 构造握手响应
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket",null,false);
        handshaker = factory.newHandshaker(request);
        if (null == handshaker){
            // 当前浏览器不支持websocket
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        }else {
            handshaker.handshake(context.channel(),request);
        }
    }

}
