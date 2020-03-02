package org.dean.duck.core.io.nio.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/10
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class BufferHub {
	public static void main(String[] args) {
//        buffer_basic_examp();
//        buffer_properties();
//        buffer_compact_clear();
//        buffer_equals();
//        buffer_compare();
//        buffer_get();
//        buffer_put();
		buffer_wrap();
		buffer_slice();
	}

	private static void buffer_slice() {
		char[] chars = new char[100];
		CharBuffer charBuffer = CharBuffer.wrap(chars);
		charBuffer.position(12).limit(21);
		// 创建一个映射到数组位置12-20的buffer对象
		CharBuffer charBuffer1 = charBuffer.slice();
	}

	/**
	 * 包装创建缓冲区，但不分配空间来存储数据元素
	 */
	private static void buffer_wrap() {
		char[] chars = new char[100];
		// 创建了一个position为12，limit值为54，容量为chars.length的缓冲区
		CharBuffer charBuffer = CharBuffer.wrap(chars, 12, 42);


	}


	private static void buffer_put() {
		CharBuffer charBuffer = CharBuffer.allocate(100);
		String string = "hello,world";
		charBuffer.put(string, 0, 5);
		charBuffer.flip();
		while (charBuffer.hasRemaining()) {
			System.out.print(charBuffer.get());
		}
		System.out.println();

	}


	private static void buffer_get() {
		CharBuffer charBuffer = CharBuffer.allocate(100);
		System.out.println("初始>>>positin:" + charBuffer.position() + ",limit:" + charBuffer.limit() + ",capacity:" + charBuffer.capacity() + ",remaining:" + charBuffer.remaining());
		charBuffer.put("hello,world");
		System.out.println("put操作后>>>positin:" + charBuffer.position() + ",limit:" + charBuffer.limit() + ",capacity:" + charBuffer.capacity() + ",remaining:" + charBuffer.remaining());
		charBuffer.flip();
		System.out.println("flip操作后>>>position:" + charBuffer.position() + ",limit:" + charBuffer.limit() + ",capacity:" + charBuffer.capacity() + ",remaining:" + charBuffer.remaining());
		char[] chars = new char[100];
		charBuffer.get(chars, 0, 5);
		for (char c : chars) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("get操作后>>>positin:" + charBuffer.position() + ",limit:" + charBuffer.limit() + ",capacity:" + charBuffer.capacity() + ",remaining:" + charBuffer.remaining());
		charBuffer.get(chars, 0, 5);
		for (char c : chars) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("get操作后>>>positin:" + charBuffer.position() + ",limit:" + charBuffer.limit() + ",capacity:" + charBuffer.capacity() + ",remaining:" + charBuffer.remaining());
	}

	/**
	 * 比较
	 */
	private static void buffer_compare() {
		CharBuffer charBuffer1 = CharBuffer.allocate(10);
		charBuffer1.put("123");
		CharBuffer charBuffer2 = CharBuffer.allocate(10);
		charBuffer2.put("12345");
		System.out.println(charBuffer2.compareTo(charBuffer1));
		System.out.println(charBuffer1.compareTo(charBuffer2));
		CharBuffer charBuffer3 = CharBuffer.allocate(8);
		charBuffer3.put("123");
		System.out.println(charBuffer1.compareTo(charBuffer3));
		CharBuffer charBuffer4 = CharBuffer.allocate(10);
		charBuffer4.put("122");
		System.out.println("position = " + charBuffer1.position() + ",limit = " + charBuffer1.limit() + ",capacity = " + charBuffer1.capacity());
		System.out.println(charBuffer1.compareTo(charBuffer4));
	}


	private static void buffer_equals() {
		CharBuffer charBuffer1 = CharBuffer.allocate(10);
		charBuffer1.put("123");
		CharBuffer charBuffer2 = CharBuffer.allocate(10);
		charBuffer2.put("123");
		System.out.println(charBuffer1.equals(charBuffer2));
		CharBuffer charBuffer3 = CharBuffer.allocate(8);
		charBuffer3.put("123");
		System.out.println(charBuffer1.equals(charBuffer3));
		System.out.println(charBuffer2.equals(charBuffer3));

		ByteBuffer byteBuffer = ByteBuffer.allocate(10);
		byteBuffer.put("123".getBytes());
		System.out.println(charBuffer1.equals(byteBuffer));


	}

	private static void buffer_compact_clear() {
		CharBuffer charBuffer = CharBuffer.allocate(48);
		System.out.println("starting===========");
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		charBuffer.put("123456789".toCharArray());
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		charBuffer.flip();
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		for (int i = 0; i < 5; i++) {
			System.out.print(charBuffer.get());
		}
		System.out.println();
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		charBuffer.compact();
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		System.out.println(charBuffer.hasRemaining());
		System.out.println(charBuffer.get(2));
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		System.out.println(charBuffer.get());
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());
		charBuffer.clear();
		System.out.println("position = " + charBuffer.position() + ",limit = " + charBuffer.limit() + ",capacity = " + charBuffer.capacity());

	}

	private static void buffer_properties() {
		ByteBuffer buffer = ByteBuffer.allocate(48);
		System.out.println("start.position=" + buffer.position());
		System.out.println("start.limit=" + buffer.limit());
		System.out.println("start.capacity=" + buffer.capacity());
		// write to buffer
		buffer.put("123".getBytes());
		System.out.println("after putting data to buffer=====================");
		System.out.println("current.position=" + buffer.position());
		System.out.println("current.limit=" + buffer.limit());
		System.out.println("current.capacity=" + buffer.capacity());
		// 切换到读模式
		buffer.flip();
		System.out.println("after buffer flip======================");
		System.out.println("current.position=" + buffer.position());
		System.out.println("current.limit=" + buffer.limit());
		System.out.println("current.capacity=" + buffer.capacity());
		while (buffer.hasRemaining()) {
			System.out.print((char) buffer.get());
		}
		System.out.println();
		System.out.println("current.position=" + buffer.position());
		System.out.println("current.limit=" + buffer.limit());
		System.out.println("current.capacity=" + buffer.capacity());
		buffer.clear();


	}

	private static void buffer_basic_examp() {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\pypy\\file2write1.txt", "rw")
		) {
			FileChannel fileChannel = randomAccessFile.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(48);
			int bytesread = fileChannel.read(byteBuffer);
			while (bytesread != -1) {
				// make buffer ready for read
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					System.out.print((char) byteBuffer.get());
				}
				byteBuffer.compact();
				bytesread = fileChannel.read(byteBuffer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
