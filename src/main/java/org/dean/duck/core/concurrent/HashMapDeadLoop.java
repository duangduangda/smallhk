package org.dean.duck.core.concurrent;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @description: HashMap死循环
 * @author: dean
 * @create: 2019/06/25 19:49
 */
public class HashMapDeadLoop {

	private HashMap<Integer, Integer> hash = Maps.newHashMap();

	public HashMapDeadLoop() {
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				hash.put(new Integer(i), i);
			}
			System.out.println("thread 1 over");

		});

		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				hash.put(new Integer(i), i);
			}
			System.out.println("thread 2 over");
		});
		thread1.start();
		thread2.start();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new HashMapDeadLoop();
		}
		System.out.println("end");

	}
}
