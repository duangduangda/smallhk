package org.dean.duck.core.concurrent;

/**
 * @description: 两个线程交替打印
 * @author: dean
 * @create: 2019/07/08 20:29
 */
public class TwoThread {
	private static volatile boolean flag = true;

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (this) {
						if (!flag) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						flag = false;
						System.out.println(Thread.currentThread().getName());
						notifyAll();
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (this) {
						if (flag) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						flag = true;
						System.out.println(Thread.currentThread().getName());
						notifyAll();
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
}
