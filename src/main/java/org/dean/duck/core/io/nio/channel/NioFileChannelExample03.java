package org.dean.duck.core.io.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author eric
 * @description 使用一个buffer实现文件内容的拷贝
 */
public class NioFileChannelExample03 {
	public static void main(String[] args) throws IOException {
		// 创建输入流
		FileInputStream fileInputStream = new FileInputStream("data.txt");
		FileChannel fileChannel1 = fileInputStream.getChannel();

		// 创建输出流
		FileOutputStream fileOutputStream = new FileOutputStream("data_copy.txt");
		FileChannel fileChannel2 = fileOutputStream.getChannel();

		// 创建buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(256);

		while (true) {
			// position复位，进行下一次操作
			byteBuffer.clear();
			// 读文件
			if (fileChannel1.read(byteBuffer) == -1) {
				break;
			}
			// 切换buffer的模式
			byteBuffer.flip();
			// 写文件
			fileChannel2.write(byteBuffer);
		}

		fileInputStream.close();
		fileOutputStream.close();
	}
}
