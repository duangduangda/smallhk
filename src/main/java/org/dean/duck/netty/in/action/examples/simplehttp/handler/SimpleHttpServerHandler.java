package org.dean.duck.netty.in.action.examples.simplehttp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * SimpleChannelInboundHandler会自动释放资源，不需要显式调用ReferenceCountUntil.release方法释放资源
 *
 * @author eric
 */
public class SimpleHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
	private static final Logger log = LoggerFactory.getLogger(SimpleHttpServerHandler.class);


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {
			// 过滤特定资源
			HttpRequest request = (HttpRequest) msg;
			URI uri = new URI(request.uri());
			if ("/favicon.ico".equalsIgnoreCase(uri.getPath())) {
				log.info("已请求过根目录，不再响应");
				return;
			}
			log.info("msg类型：" + msg.getClass());
			log.info("客户端地址：" + ctx.channel().remoteAddress());

			ByteBuf content = Unpooled.copiedBuffer("Hello,我是服务器", CharsetUtil.UTF_8);
			// 构建response
			HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

			ctx.writeAndFlush(response);

		}
	}
}
