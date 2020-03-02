package org.dean.duck.core.io.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author eric
 */
public class BasicBuffer {
	public static void main(String[] args) {
		// 创建大小为5，可以存放5个int类型
		IntBuffer buffer = IntBuffer.allocate(5);
		// 存放数据
		for (int i = 0; i < 5; i++) {
			buffer.put(i);
		}
		// 读写切换
		buffer.flip();
		// 读取数据
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
	}
}
