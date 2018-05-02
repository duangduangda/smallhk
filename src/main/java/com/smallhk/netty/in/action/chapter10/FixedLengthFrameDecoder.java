package com.smallhk.netty.in.action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

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
public class FixedLengthFrameDecoder extends ByteToMessageDecoder{

    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength){
        if (frameLength <= 0){
            throw new IllegalArgumentException("frameLength must be a positive integer:" + frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >= frameLength ){
            ByteBuf buf = in.readBytes(frameLength);
            out.add(buf);
        }
    }
}
