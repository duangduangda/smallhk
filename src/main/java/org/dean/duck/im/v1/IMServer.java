package org.dean.duck.im.v1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author eric
 */
public class IMServer {

	private static final int PORT = 7777;
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;

	public IMServer() {
		try {
			// 获取服务器端通道
			serverSocketChannel = ServerSocketChannel.open();
			// 设置为非阻塞模式
			serverSocketChannel.configureBlocking(false);
			// 绑定端口
			serverSocketChannel.bind(new InetSocketAddress(PORT));
			// 获取选择器
			selector = Selector.open();
			// 注册选择器,监听OP_ACCEPT事件
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		try {
			while (true) {
				int selected = selector.select();
				if (selected == 0) {
					continue;
				}
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isAcceptable()) {
						SocketChannel socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
						System.out.println(socketChannel.getRemoteAddress() + "上线了");
					}
					if (key.isReadable()) {
						SocketChannel socketChannel = null;
						try {
							socketChannel = (SocketChannel) key.channel();
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							int read = socketChannel.read(byteBuffer);
							if (read > 0) {
								String msg = new String(byteBuffer.array());
								System.out.println("from client:" + msg);
								// 做消息转发
								dispacthMessage(socketChannel, byteBuffer, msg);
							}

						} catch (Exception e) {
							System.out.println(socketChannel.getRemoteAddress() + "下线了...");
							// 取消注册
							key.channel();
							// 关闭通道
							socketChannel.close();
						}
					}
					iterator.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dispacthMessage(SocketChannel socketChannel, ByteBuffer byteBuffer, String msg) throws IOException {
		Set<SelectionKey> keys = selector.keys();
		for (SelectionKey targetKey : keys) {
			SelectableChannel targetChannel = targetKey.channel();
			// 排除自己
			if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
				SocketChannel dest = (SocketChannel) targetChannel;
				byteBuffer.flip();
				dest.write(byteBuffer.wrap(msg.getBytes()));
			}
		}
	}

	public static void main(String[] args) {
		new IMServer().listen();
	}
}
