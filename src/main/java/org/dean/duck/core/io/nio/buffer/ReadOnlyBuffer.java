package org.dean.duck.core.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author eric
 * @description 只读buffer, 不能再放入新的元素
 */
public class ReadOnlyBuffer {
	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(64);
		for (int i = 0; i < 64; i++) {
			byteBuffer.put((byte) i);
		}

		byteBuffer.flip();

		ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
		while (readOnlyBuffer.hasRemaining()) {
			System.out.println(readOnlyBuffer.get());
		}
	}
}
