package com.smallhk.core.rpc.netty;

import com.smallhk.netty.in.action.codec.marshalling.MarshallingCodecFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/9/4
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class RpcClientInitializer extends ChannelInitializer<Channel>{

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
        channel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
        channel.pipeline().addLast(new RpcClientHandler());
    }
}
