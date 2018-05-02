package com.smallhk.netty.in.action.chapter10;

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
public class AbsIntegerEncoderTest {
    @Test
    public void testEncoder(){
        ByteBuf input = Unpooled.buffer();
        for(int i = 1;i < 10;i++){
            input.writeInt(i * -1);
        }
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(embeddedChannel.writeOutbound(input));
        assertTrue(embeddedChannel.finish());
        for (int i = 1;i < 10;i++){
            assertEquals(i, embeddedChannel.readOutbound());
        }
        assertNull(embeddedChannel.readOutbound());

    }
}
