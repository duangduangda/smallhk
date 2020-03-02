package org.dean.duck.core.io.nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author eric
 */
public class NioFileChannelExample01 {

	public static void main(String[] args) throws IOException {
		String message = "Hello,java";
		// 将数据写入buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.put(message.getBytes());
		// 切换buffer的模式为读模式
		byteBuffer.flip();

		// 创建输出流
		FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
		// 从输出流获取channel
		FileChannel fileChannel = fileOutputStream.getChannel();
		// 将buffer数据写入到channel中
		fileChannel.write(byteBuffer);
		fileOutputStream.close();
	}
}
