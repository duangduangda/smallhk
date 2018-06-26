package com.smallhk.netty.in.action.protobuf;

import com.smallhk.netty.in.action.codec.serializable.SubReqServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;

/**
 * Title. <br>
 * Description.
 * protobuf 本身并不支持半包，只是负责编码，在使用之前必须先解决半包问题，解决的方式有：
 * 1. 使用netty提供的ProtobufVarint32FrameDecoder
 * 2. 继承netty提供的通用半包处理器LengthFieldBasedFrameDecoder
 * 3. 继承ByteToMessageDecoder，自行进行处理半包问题
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
public class ProtobufServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler()).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 半包问题
                    socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
                    socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    socketChannel.pipeline().addLast(new ProtobufEncoder());
                    socketChannel.pipeline().addLast(new ProtoSubReqServerHandler());
                }
            });

            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
