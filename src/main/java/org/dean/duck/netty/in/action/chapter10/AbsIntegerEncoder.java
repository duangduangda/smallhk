package org.dean.duck.netty.in.action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

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
public class AbsIntegerEncoder extends  MessageToMessageEncoder<ByteBuf> {
        @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf input, List<Object> out) throws Exception {
            while (input.readableBytes() >= 4) {
                int value = Math.abs(input.readInt());
                out.add(value);
            }
    }
}

