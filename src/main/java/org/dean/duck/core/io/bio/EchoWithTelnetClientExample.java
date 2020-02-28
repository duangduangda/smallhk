package org.dean.duck.core.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池的方式
 *
 * @author eric
 */
public class EchoWithTelnetClientExample {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(9090);
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("获取一个连接");
			service.execute(() -> handler(socket));
		}
	}

	private static void handler(Socket socket) {
		byte[] bytes = new byte[1024];
		try {
			while (true) {
				InputStream inputStream = socket.getInputStream();
				int read = inputStream.read(bytes);
				if (read == -1) {
					break;
				}
				System.out.println(Thread.currentThread().getName() + "获取信息：" + new String(bytes, 0, read));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
