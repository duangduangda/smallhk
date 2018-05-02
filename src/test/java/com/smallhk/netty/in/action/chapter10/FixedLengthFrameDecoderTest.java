package com.smallhk.netty.in.action.chapter10;

import com.smallhk.netty.in.action.chapter10.FixedLengthFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

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
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFrameDecoded(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0;i < 9;i++){
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // write bytes
        assertTrue(embeddedChannel.writeInbound(input.retain()));
        assertTrue(embeddedChannel.finish());
        // read message
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertNull(embeddedChannel.readInbound());
    }


    @Test
    public void testFrameDecoded2(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0;i < 9;i++){
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        assertFalse(embeddedChannel.writeInbound(input.readBytes(2)));
        assertTrue(embeddedChannel.writeInbound(input.readBytes(7)));
        assertTrue(embeddedChannel.finish());
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertEquals(buf.readBytes(3),embeddedChannel.readInbound());
        assertNull(embeddedChannel.readInbound());
    }
}
