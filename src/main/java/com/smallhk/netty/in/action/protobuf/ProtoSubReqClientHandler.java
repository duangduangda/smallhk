package com.smallhk.netty.in.action.protobuf;

import com.smallhk.netty.in.action.codec.serializable.SubscribeReq;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/26
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ProtoSubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context){
        for (int i = 0;i < 10;i++){
            context.write(subReq(i));
        }
        context.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(i);
        builder.setAddress("北京市海淀区");
        builder.setProductName("netty in action");
        builder.setUserName("eric");
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        System.out.println("receive server response:" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }
}
