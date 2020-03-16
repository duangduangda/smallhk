package org.dean.duck.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author eric
 */
public class NioFileClient {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 7001));
		FileInputStream inputStream = new FileInputStream("/Users/eric/IdeaProjects/Duck/src/main/resources/log4j.properties");
		FileChannel fileChannel = inputStream.getChannel();
		fileChannel.transferTo(0, fileChannel.size(), socketChannel);
		fileChannel.close();
	}
}
