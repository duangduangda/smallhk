package org.dean.duck.core.io.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author eric
 * @description MappedByteBuffer, 可让文件直接在内存(堆外内存)修改，不需要操作系统拷贝一次(零拷贝)
 */
public class MappedByteBufferExample {
	public static void main(String[] args) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("data.txt", "rw");
		FileChannel fileChannel = randomAccessFile.getChannel();

		MappedByteBuffer mapByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
		mapByteBuffer.put(0, (byte) 'h');
		mapByteBuffer.put(3, (byte) 6);

		randomAccessFile.close();
		System.out.println("successfully~~~~");

	}
}
