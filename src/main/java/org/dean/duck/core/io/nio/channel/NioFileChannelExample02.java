package org.dean.duck.core.io.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author eric
 * @description 从文件读取数据并输出到控制台
 */
public class NioFileChannelExample02 {
	public static void main(String[] args) throws IOException {
		File file = new File("data.txt");
		// 创建文件输入流
		FileInputStream fileInputStream = new FileInputStream(file);
		// 获取channel
		FileChannel fileChannel = fileInputStream.getChannel();
		// 创建buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
		// 将Channel的数据写入buffer
		fileChannel.read(byteBuffer);
		// 字节buffer转化为string
		System.out.println(new String(byteBuffer.array()));

	}
}
