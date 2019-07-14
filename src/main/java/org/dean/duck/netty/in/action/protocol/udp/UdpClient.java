package org.dean.duck.netty.in.action.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/29
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class UdpClient {

    private  static final Logger logger = LoggerFactory.getLogger(UdpClient.class);

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).option(ChannelOption.SO_BROADCAST,true)
                    .channel(NioDatagramChannel.class).handler(new UdpClientHander());
            Channel channel = bootstrap.bind(0).sync().channel();

            // 向网段内的所有机器广播udp消息
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("hello",UTF_8),
                    new InetSocketAddress("255.255.255.255",8080))).channel();

            if (channel.closeFuture().await(15000)){
                logger.info("Request time out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
