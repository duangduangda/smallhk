package com.smallhk.netty.in.action.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    /**
     <p>
     为什么在服务器中不使用SimpleChannelInboundHandler呢？因为服务器要返回相同的消息给客户端，在服务器执行完成写操作之前不能释放调
     用读取到的消息，因为写操作是异步的，一旦写操作完成后，Netty中会自动释放消息。
     </p>

     */

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception{
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
        context.write(msg);
        //手动释放消息
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws  Exception{
        context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause) throws Exception {
        cause.printStackTrace();
        context.close();
    }
}
