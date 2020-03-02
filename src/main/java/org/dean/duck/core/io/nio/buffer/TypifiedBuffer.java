package org.dean.duck.core.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author eric
 * @description buffer支持类型化
 */
public class TypifiedBuffer {
	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
		byteBuffer.putInt(10);
		byteBuffer.putLong(190L);
		byteBuffer.putChar('l');
		byteBuffer.putShort((short) 4);

		// 切换模式
		byteBuffer.flip();
		// 输出须按放入的类型顺序，否则会抛出异常BufferUnderflowException
		System.out.println(byteBuffer.getInt());
		System.out.println(byteBuffer.getLong());
		System.out.println(byteBuffer.getChar());
		System.out.println(byteBuffer.getShort());
	}
}
