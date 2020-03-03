package org.dean.duck.core.io.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/27
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ScatterGatherExample {
	public static void main(String[] args) {
//		channel_scatter_read();
//		channel_gather_write();
		scatter_gather();
	}

	/**
	 *
	 */
	private static void scatter_gather() {
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
			serverSocketChannel.bind(inetSocketAddress);

			// 创建buffer数组
			ByteBuffer[] byteBuffers = new ByteBuffer[2];
			byteBuffers[0] = ByteBuffer.allocate(5);
			byteBuffers[1] = ByteBuffer.allocate(3);
			int lengthOfMessage = 8;

			SocketChannel socketChannel = serverSocketChannel.accept();
			while (true) {
				long read = 0;
				while (read < lengthOfMessage) {
					long r = socketChannel.read(byteBuffers);
					read += r;
					System.out.println("read=" + read);
					// 打印position信息
					Arrays.asList(byteBuffers).stream().map(byteBuffer ->
							"Position:" + byteBuffer.position() + ",limit:" +
									byteBuffer.limit())
							.forEach(System.out::println);
				}
				// 切换buffer模式
				Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.flip());

				long write = 0;
				while (write < lengthOfMessage) {
					long w = socketChannel.write(byteBuffers);
					write += w;

				}

				Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::clear);
				// 打印position信息
				long finalRead = read;
				long finalWrite = write;
				Arrays.asList(byteBuffers).stream().map(byteBuffer ->
						"Read:" + finalRead + ",write:" +
								finalWrite + ",lengthOfMessage:" + lengthOfMessage)
						.forEach(System.out::println);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void channel_gather_write() {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("channel_gather_write.txt", "rw");
		     FileChannel fileChannel = randomAccessFile.getChannel()
		) {
			ByteBuffer header = ByteBuffer.allocate(10);
			header.put("123".getBytes());
			ByteBuffer body = ByteBuffer.allocate(10);
			body.put("456".getBytes());
			ByteBuffer[] byteBuffers = {header, body};
			fileChannel.write(byteBuffers);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void channel_scatter_read() {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("data.txt", "rw")) {
			FileChannel fileChannel = randomAccessFile.getChannel();
			ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
			ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
			fileChannel.read(byteBuffers);
			byteBuffer1.flip();
			System.out.println("开始读取buffer1的数据");
			while (byteBuffer1.hasRemaining()) {
				System.out.print((char) byteBuffer1.get());
			}
			byteBuffer1.clear();
			System.out.println();
			byteBuffer2.flip();
			System.out.println("开始读取buffer2的数据");
			while (byteBuffer2.hasRemaining()) {
				System.out.print((char) byteBuffer2.get());
			}
			byteBuffer2.clear();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
