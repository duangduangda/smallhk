package com.smallhk.netty.in.action.protobuf;

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
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class ProtoSubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("eric".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscibe req:["
                    + req.toString() + "]"
            );
            context.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqId) {
        SubscribeRespProto.SubscribeResp.Builder  builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqId(subReqId);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed,3 days later,sent to the desingnated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }
}
