package com.smallhk.core.dp.singleton;
/**
 * @description 双重检测锁式单例 ，这种实现方式会造成指令重排序问题，导致双重检测机制失效。
 * @author eric
 * @since 2016年5月29日 下午6:30:20
 * @version 1.0
 *
 */
public class SingletonDemo3{
	private static SingletonDemo3 instance = null;

	private SingletonDemo3(){}

	public static SingletonDemo3 getInstance(){
		if(null == instance){
			SingletonDemo3 sc;
			synchronized(SingletonDemo3.class){
				sc = instance;
				if(null == sc){
					synchronized(SingletonDemo3.class){
						if(null == sc){
							sc = new SingletonDemo3();
						}
					}
					instance = sc;
				}
			}
			
		}
	return instance;
	}
}
