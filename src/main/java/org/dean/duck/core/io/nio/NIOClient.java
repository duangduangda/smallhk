package org.dean.duck.core.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

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
public class NIOClient {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);

		InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8000);
		if (!socketChannel.connect(inetSocketAddress)) {
			while (!socketChannel.finishConnect()) {
				log.info("连接中，客户端未阻塞，可以继续进行其他操作~~~");
			}
		}
		ByteBuffer byteBuffer = ByteBuffer.wrap("hello,nio".getBytes(StandardCharsets.UTF_8));
		socketChannel.write(byteBuffer);
		System.in.read();
	}
}
