package org.dean.duck.core.io.nio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/9
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
@Slf4j
public class NIOServer {

	public static void main(String[] args) throws IOException {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("nio_server").build();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 3, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024), threadFactory);
		ServerSocketChannel serverSocketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(8000));
			// 获取选择器
			Selector selector = Selector.open();
			// 注册监听事件
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				int num = selector.select(1000L);
				if (num == 0) {
					log.info("服务器等待1秒，未获取任何连接");
					continue;
				}

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
				while (selectionKeyIterator.hasNext()) {
					SelectionKey selectionKey = selectionKeyIterator.next();
					if (selectionKey.isAcceptable()) {
						SocketChannel socketChannel = serverSocketChannel.accept();
						log.info("获取连接：" + socketChannel.hashCode());
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) {
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						selectionKey.cancel();
						socketChannel.configureBlocking(false);
						threadPoolExecutor.execute(() -> {
							try {
								ByteBuffer request = ByteBuffer.allocate(1024);
								socketChannel.read(request);
								request.flip();
								log.info("当前线程" + Thread.currentThread().getId() + ",收到请求：" + new String(request.array()).trim());
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									socketChannel.register(selector, SelectionKey.OP_READ);
								} catch (ClosedChannelException e) {
									e.printStackTrace();
								}
							}

						});
					}
				}
				selectionKeyIterator.remove();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != serverSocketChannel) {
				serverSocketChannel.close();
			}
		}

	}

}
