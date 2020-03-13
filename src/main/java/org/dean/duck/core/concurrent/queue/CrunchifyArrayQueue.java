package org.dean.duck.core.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author eric
 */
public class CrunchifyArrayQueue {
	public static void main(String[] args) {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		try {

			for (int i = 0; i < 15; i++) {
				// Error once queue full
				queue.add("No:" + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
