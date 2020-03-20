package org.dean.duck.netty.in.action.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class UnpooledExample {
	public static void main(String[] args) {
		ByteBuf buffer = Unpooled.buffer(10);
		for (int i = 0; i < 8; i++) {
			buffer.writeByte(i);
		}
		for (int i = 0; i < buffer.capacity(); i++) {
			System.out.println(buffer.getByte(i));
		}
		ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,buffer", CharsetUtil.UTF_8);
		if (byteBuf.hasArray()) {
			byte[] contents = byteBuf.array();
			System.out.println(new String(contents, CharsetUtil.UTF_8));
		}
		System.out.println(byteBuf.capacity());
		System.out.println(byteBuf.readableBytes());
		System.out.println(byteBuf.getCharSequence(0, 4, CharsetUtil.UTF_8));
		System.out.println(byteBuf.getCharSequence(11, 3, CharsetUtil.UTF_8));
	}
}
