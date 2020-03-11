package org.dean.duck.im.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author eric
 */
public class IMClient {
	private static final String REMOTE_ADDRESS = "127.0.0.1";
	private static final int PORT = 7777;

	private SocketChannel socketChannel;
	private Selector selector;
	private String serverAddress;
	private int port;
	private String userName;


	public IMClient(String serverAddress, int port) throws IOException {
		this.serverAddress = serverAddress;
		this.port = port;

		socketChannel = SocketChannel.open(new InetSocketAddress(REMOTE_ADDRESS, PORT));
		socketChannel.configureBlocking(false);

		selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_READ);
		userName = socketChannel.getLocalAddress().toString();
	}

	private void send(String message) {
		message = userName + "says:" + message;
		try {
			socketChannel.write(ByteBuffer.wrap(message.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() {
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
					if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						int read = channel.read(byteBuffer);
						if (read > 0) {
							System.out.println(new String(byteBuffer.array()));
						}
					}
					iterator.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		IMClient client = new IMClient(REMOTE_ADDRESS, PORT);
		new Thread(() -> {
			while (true) {
				client.read();
				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			client.send(scanner.next());
		}

	}
}
