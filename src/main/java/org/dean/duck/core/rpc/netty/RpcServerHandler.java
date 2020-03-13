package org.dean.duck.core.rpc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.dean.duck.core.rpc.netty.protocol.RpcRequest;
import org.dean.duck.core.rpc.netty.protocol.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Title. <br> Description.
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
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

	private static final Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);

	private ThreadPoolExecutor threadPoolExecutor;

	public RpcServerHandler(ThreadPoolExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
		threadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				logger.info("Request:" + rpcRequest.getRequestId() + ",classname:" + rpcRequest.getClassName() + ",method:" + rpcRequest.getMethodName());
				RpcResponse response = new RpcResponse();
				try {
					Object result = handlerRequest(rpcRequest);
					response.setRequestId(rpcRequest.getRequestId());
					response.setResult(result);
				} catch (Throwable e) {
					response.setError(e.toString());
					logger.error("RPC Server handle request error", e);
				}
				channelHandlerContext.writeAndFlush(response).addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture channelFuture) throws Exception {
						logger.debug("Send response for request " + rpcRequest.getRequestId());
					}
				});
			}
		});
	}

	/**
	 * 处理请求
	 *
	 * @param rpcRequest
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	private Object handlerRequest(RpcRequest rpcRequest) throws Exception {
		String className = rpcRequest.getClassName();
		String methodName = rpcRequest.getMethodName();
		Class[] types = rpcRequest.getParameterTypes();
		Object[] arguments = rpcRequest.getParameters();
		Class clazz = Class.forName(className);
		Method method = clazz.getMethod(methodName, types);
		return method.invoke(clazz.newInstance(), arguments);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext context) throws Exception {
		ByteBuf byteBuf = Unpooled.copiedBuffer("success calling fromm client".getBytes());
		context.writeAndFlush(byteBuf).addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				logger.info("成功推送数据至客户端");
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
		cause.printStackTrace();
		context.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext context) {
		logger.info("server start.............");
	}
}
