package org.dean.duck.core.io.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author eric
 * @description 拷贝文件transferFrom方法
 */
public class NioFileChannelExample04 {
	public static void main(String[] args) throws IOException {
		// 创建输入流
		FileInputStream fileInputStream = new FileInputStream("data.txt");
		FileChannel sourceChannel = fileInputStream.getChannel();
		// 创建输出流
		FileOutputStream fileOutputStream = new FileOutputStream("data_copy1.txt");
		FileChannel destChannel = fileOutputStream.getChannel();
		// 使用transferFrom完成拷贝
		destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		// 关闭流
		sourceChannel.close();
		destChannel.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
}
