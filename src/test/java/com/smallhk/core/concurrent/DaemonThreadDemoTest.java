package com.smallhk.core.concurrent;

import java.util.Scanner;

import com.smallhk.core.concurrent.DaemonThreadDemo;

/**
 * @description 
 * @author dongyh
 * @since 2016年4月1日 下午3:06:38
 * @version 1.0
 *
 */
public class DaemonThreadDemoTest {

	public static void main(String[] args) {
		System.out.println("进入主线程...");
		
		DaemonThreadDemo daemonThread = new DaemonThreadDemo();
		Thread thread = new Thread(daemonThread);
		thread.setDaemon(true);
		thread.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.next();
		
		System.out.println("退出主线程...");
	}

}
