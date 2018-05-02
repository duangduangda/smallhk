package com.smallhk.netty.in.action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class FrameChunkDecoderTest {

    @Test
    public void testFrameDecoder(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0;i < 9;i++){
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FrameChunkDecoder(3));
        assertTrue(embeddedChannel.writeInbound(input.readBytes(2)));
        try{
            embeddedChannel.writeInbound(input.readBytes(4));
            fail();
        }catch (TooLongFrameException e){

        }

        assertTrue(embeddedChannel.writeInbound(input.readBytes(3)));
        assertTrue(embeddedChannel.finish());

        assertEquals(buf.readBytes(2),embeddedChannel.readInbound());
        assertEquals(buf.skipBytes(4).readBytes(3),embeddedChannel.readInbound());

    }
}
