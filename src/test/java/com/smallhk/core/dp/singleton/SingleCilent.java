package com.smallhk.core.dp.singleton;

import java.util.concurrent.CountDownLatch;

import com.sun.webkit.ThemeClient;

/**
 * @description 多线程环境下5种单例模式的效率 
 * @author dongyh
 * @since 2016年5月29日 下午6:37:13
 * @version 1.0
 *
 */
public class SingleCilent {

	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		int threadNum = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
		
		for(int i = 0;i < threadNum;i++){
			new Thread(new Runnable(){
				public void run(){
					for(int i = 0;i < 100000;i++){
						Object o = SingletonDemo3.getInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		
		countDownLatch.await();//主线程阻塞，知道计数器变为0，才继续往下执行
		
		long end = System.currentTimeMillis();
		System.out.println("总耗时："  + (end - start));
	}

}
