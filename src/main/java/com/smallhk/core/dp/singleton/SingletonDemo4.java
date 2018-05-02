package com.smallhk.core.dp.singleton;
/**
 * @description 静态内部类式单例模式，线程安全，调用效率高，可以延时加载 
 * @author eric
 * @since 2016年5月29日 下午6:33:11
 * @version 1.0
 *
 */
public class SingletonDemo4{

	private SingletonDemo4(){}

	private static class SingletonClassInstance{
		private static final SingletonDemo4 instance = new SingletonDemo4(); 
	}

	public static SingletonDemo4 getInstance(){
		return SingletonClassInstance.instance;
	}
}

