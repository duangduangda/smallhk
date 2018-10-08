package com.smallhk.netty.in.action.protocol.netty;

import com.smallhk.netty.in.action.codec.marshalling.MarshallingCodecFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/10/6
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NettyPrivateProtocolClient {
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        new NettyPrivateProtocolClient().connect();
    }

    private void connect() {

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new LoginAuthRequestHandler());
                            ch.pipeline().addLast(new HeartBeatRequestHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1",8080)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.SECONDS.sleep(5);
                        try{
                            connect();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}
