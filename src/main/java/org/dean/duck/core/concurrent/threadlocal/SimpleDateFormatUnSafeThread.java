package org.dean.duck.core.concurrent.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat线程安全示例
 *
 * @author eric
 */
public class SimpleDateFormatUnSafeThread {
	public static void main(String[] args) {
//		threadUnsafe();
//		threadSafe1();
		threadSafe2();
	}

	/**
	 * 使用threadLocal的方式
	 */
	private static void threadSafe2() {
		ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String dateTime = "2019-12-30 15:35:34";
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (int i1 = 0; i1 < 5; i1++) {
					try {
						System.out.println(
								Thread.currentThread().getName() + "\t" + simpleDateFormatThreadLocal.get().parse(dateTime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	/**
	 * dateFormat,每个线程都new一个,但是会比较浪费资源
	 */
	private static void threadSafe1() {
		String dateTime = "2019-12-30 15:35:34";
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (int i1 = 0; i1 < 5; i1++) {
					try {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						System.out.println(
								Thread.currentThread().getName() + "\t" + dateFormat.parse(dateTime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	/**
	 * calendar 是用在 format 和 parse 时用的。另外，因为 calendar 作为一个成员变量，在多线程场景下，会发生资源共享造成前后不一致的问题。
	 */
	private static void threadUnsafe() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = "2019-12-30 15:35:34";
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (int i1 = 0; i1 < 5; i1++) {
					try {
						System.out.println(
								Thread.currentThread().getName() + "\t" + dateFormat.parse(dateTime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
