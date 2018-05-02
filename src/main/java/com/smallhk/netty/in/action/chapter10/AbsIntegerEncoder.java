package com.smallhk.netty.in.action.chapter10;

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
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
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

