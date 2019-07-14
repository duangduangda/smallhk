package org.dean.duck.netty.in.action.chapter9;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.nio.ByteBuffer;
import java.util.jar.Attributes;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/2
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ChannleOptionExample {
    public static void main(String[] args) throws InterruptedException {
        final AttributeKey<Integer> id = AttributeKey.newInstance("ID");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {

            @Override
            protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                System.out.println("Received data");
                byteBuf.clear();
            }

            @Override
            public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception{
                Integer idValue = channelHandlerContext.channel().attr(id).get();
                System.out.println("idValue = " + idValue);
            }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",2048).sync();
        channelFuture.syncUninterruptibly();


    }
}
