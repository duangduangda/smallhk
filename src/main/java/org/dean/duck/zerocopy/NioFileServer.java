package org.dean.duck.zerocopy;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author eric
 */
public class NioFileServer {
	public static void main(String[] args) throws IOException {
//		blocking();
		nonBlocking();
	}

	private static void nonBlocking() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(7001));
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			int selected = selector.select();
			if (selected <= 0) {
				continue;
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if (selectionKey.isAcceptable()) {
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
					System.out.println(socketChannel.getRemoteAddress() + "连接成功~");
				}

				if (selectionKey.isReadable()) {
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					int read = channel.read(byteBuffer);
					if (read < 0) {
						break;
					}
					System.out.println(new String(byteBuffer.array()));
					byteBuffer.rewind();
				}
			}
			iterator.remove();
		}

	}

	private static void blocking() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(7001));
		ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
		while (true) {
			// 使用accept的方式进行监听，如果设置configureBlocking=false会导致启动后socketChannel获取不到
			SocketChannel socketChannel = serverSocketChannel.accept();
			int read = 0;
			while (read != -1) {
				read = socketChannel.read(byteBuffer);
				if (read < 0) {
					break;
				}
				System.out.println(new String(byteBuffer.array()));
				byteBuffer.rewind();
			}
		}
	}
}
