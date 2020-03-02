package org.dean.duck.core.io.nio.buffer;

import java.nio.CharBuffer;

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
public class BufferFillDrain {
	private static int index = 0;

	private static String[] strings = {
			"A random string value",
			"The product of an infinite number of monkeys",
			"Hey hey we're the Monkees",
			"Opening act for the Monkees:Jim Hendrix",
			"'Scuse me while I kiss this fly'",
			"Help me!Help me!"

	};

	public static void main(String[] args) {
		CharBuffer charBuffer = CharBuffer.allocate(100);
		while (fillBuffer(charBuffer)) {
			charBuffer.flip();
			drainBuffer(charBuffer);
			charBuffer.clear();
		}
	}

	private static void drainBuffer(CharBuffer charBuffer) {
		while (charBuffer.hasRemaining()) {
			System.out.print(charBuffer.get());
		}
		System.out.println(" ");
	}

	private static boolean fillBuffer(CharBuffer charBuffer) {
		if (index >= strings.length) {
			return false;
		}
		String string = strings[index++];
		for (int i = 0; i < string.length(); i++) {
			charBuffer.put(string.charAt(i));
		}
		return true;
	}
}
