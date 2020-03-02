package org.dean.duck.core.io.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/28
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SocketChannelExample {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = null;
		try {
			// 创建连接
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

			if (!socketChannel.finishConnect()) {
				System.out.println("waiting for connect to host............");
			}
			// 读取数据
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			ByteBuffer[] byteBuffers = {header, body};
			long bytesread = socketChannel.read(byteBuffers);
			while (bytesread != -1) {
				bytesread = socketChannel.read(byteBuffers);
			}

			header.flip();
			body.flip();

			while (header.hasRemaining()) {
				System.out.print((char) header.get());
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != socketChannel) {
				socketChannel.close();
			}
		}
	}
}
