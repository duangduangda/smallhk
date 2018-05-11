package com.smallhk.netty.in.action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/2
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class EmBeddedChannelTest {

    @Test
    public void test(){
        ByteBuf buf = Unpooled.buffer();
        for(int i = 0 ;i < 3;i++){
            buf.writeInt(i);
        }
        ChannelHandler simpleChannelHandler = new SimpleChannelInboundHandler() {
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
                System.out.println("Received message" + o);
                System.out.println("Received finished.");
                channelHandlerContext.fireChannelRead(o);
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new AbsIntegerEncoder(),simpleChannelHandler);
        embeddedChannel.writeInbound(buf);

        System.out.println("embeddedChannel readInbound:"+embeddedChannel.readInbound());
        System.out.println("embeddedChannel readInbound:"+embeddedChannel.readInbound());
        System.out.println("embeddedChannel readInbound:"+embeddedChannel.readInbound());
    }

}
