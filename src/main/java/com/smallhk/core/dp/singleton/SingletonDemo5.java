package com.smallhk.core.dp.singleton;
/**
 * @description 枚举式单例模式，线程安全，调用效率高，但是不支持延时加载 
 *              可以天然防止反射和反序列化漏洞
 * @author eric
 * @since 2016年5月29日 下午6:34:25
 * @version 1.0
 *
 */
public enum SingletonDemo5{
	
	//枚举
	INSTANCE;
	
	//添加自己的操作
	public void singletonOperation(){
		
	}
}
