package org.dean.duck.core.dp.singleton;

import java.io.Serializable;

/**
 * @description 饿汉式单例，线程安全，效率高，不支持延时加载
 * @author eric
 * @since 2016年5月29日 下午6:29:30
 * @version 1.0
 *
 */
public class SingletonDemo1 implements Serializable{
  
	private static final long serialVersionUID = 1L;
	private static SingletonDemo1 instance = new SingletonDemo1();
   	
	private SingletonDemo1(){
		if(null != instance){
			throw new RuntimeException();//防止反射漏洞
		}
	}

	public static SingletonDemo1 getInstance(){
		return instance;
	}
	
	private Object readResolve() throws Exception{
		return instance;
	}
}
