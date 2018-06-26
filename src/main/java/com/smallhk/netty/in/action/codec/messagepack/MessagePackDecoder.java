package com.smallhk.netty.in.action.codec.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Title. <br>
 * Description. messagepack 解码器
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
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf>{

    /**
     * 将byteBuf里的数据读至二进制数组，通过messagePack进行反序列
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array));
    }
}
