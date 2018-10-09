package com.smallhk.core.rpc.netty;

import com.smallhk.netty.in.action.codec.marshalling.MarshallingCodecFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import org.jboss.marshalling.MarshallerFactory;

import java.util.concurrent.ThreadPoolExecutor;

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
public class RpcServerInitializer extends ChannelInitializer<Channel> {


    private ThreadPoolExecutor threadPoolExecutor;

    public RpcServerInitializer(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
        channel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
        channel.pipeline().addLast(new RpcServerHandler(threadPoolExecutor));
    }
}
