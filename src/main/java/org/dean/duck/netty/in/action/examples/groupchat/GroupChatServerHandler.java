package org.dean.duck.netty.in.action.examples.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author eric
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
	private static final Logger log = LoggerFactory.getLogger(GroupChatServerHandler.class);

	// 定义一个channel组，管理所有的channel,使用全局事件执行器，单例
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private static ThreadLocal<SimpleDateFormat> formatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// 消息进行转发，同时排除掉自己发出的消息
		Channel current = ctx.channel();
		channelGroup.forEach(channel -> {
			if (current != channel) {
				channel.writeAndFlush("客户：" + current.remoteAddress() + "于" + formatThreadLocal.get().format(new Date()) + "发送：" + msg + "\n");
			} else {
				channel.writeAndFlush("自己" + current.remoteAddress() + "于" + formatThreadLocal.get().format(new Date()) + "发送：" + msg + "\n");
			}
		});
	}

	/**
	 * 连接建立后执行
	 *
	 * @param ctx
	 *
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		// 将当前channel加入聊天的消息推送给其他客户,该方法会遍历ChannelGroup中的方法，并发送
		channelGroup.writeAndFlush(channel.remoteAddress() + "加入聊天\n");
		channelGroup.add(channel);
	}

	/**
	 * 断开连接
	 *
	 * @param ctx
	 *
	 * @throws Exception
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush(ctx.channel().remoteAddress() + "离开~\n");
		log.info("当前在线人数：" + channelGroup.size());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info(ctx.channel().remoteAddress() + "上线了~\n");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info(ctx.channel().remoteAddress() + "下线了~");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
