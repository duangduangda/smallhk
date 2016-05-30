package com.smallhk.core.dp.singleton;
/**
 * 
 * @description 懒汉式单例模式，支持延时加载，线程不安全，调用效率不高
 * @author dongyh
 * @since 2016年5月29日 下午6:31:44
 * @version 1.0
 *
 */
public class SingletonDemo2{
	private static SingletonDemo2 instance;
	
	private SingletonDemo2(){}

	public static synchronized SingletonDemo2 getInstance(){
		if(null == instance){
			instance = new SingletonDemo2();
		}
		return instance;
	}
}
