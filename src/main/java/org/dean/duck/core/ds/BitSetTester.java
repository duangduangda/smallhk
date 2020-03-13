package org.dean.duck.core.ds;

import java.util.BitSet;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/2/28
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class BitSetTester {
	public static void main(String[] args) {
		BitSet bitSet1 = new BitSet(16);
		BitSet bitSet2 = new BitSet(16);

		for (int i = 0; i < 16; i++) {
			if (i % 2 == 0) {
				bitSet1.set(i);
			}
			if (i % 5 == 0) {
				bitSet2.set(i);
			}
		}

		// set some bits
		System.out.println("Initial pattern in bits1");
		System.out.println(bitSet1);
		System.out.println("Initial pattern in bits2");
		System.out.println(bitSet2);

		// and bits
		bitSet2.and(bitSet1);
		System.out.println("bits2 and bits1");
		System.out.println(bitSet2);

		// ot bits
		bitSet2.or(bitSet1);
		System.out.println("bits2 OR bits1: ");
		System.out.println(bitSet2);

		// xor bits
		bitSet2.xor(bitSet1);
		System.out.println("bits2 XOR bits1: ");
		System.out.println(bitSet2);
	}
}
