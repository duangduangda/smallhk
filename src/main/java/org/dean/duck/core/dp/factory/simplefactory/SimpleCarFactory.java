package org.dean.duck.core.dp.factory.simplefactory;

import org.dean.duck.core.dp.factory.Audi;
import org.dean.duck.core.dp.factory.Byd;
import org.dean.duck.core.dp.factory.Car;

/**
 * @description 简单工厂类
 * @author eric
 * @since 2016年5月29日 下午9:35:19
 * @version 1.0
 *
 */
public class SimpleCarFactory {
	
	public static Car createCar(String type){
		if("奥迪".equals(type)){
			return new Audi();
		}else if("比亚迪".equals(type)){
			return new Byd();
		}
		return null;
	}
	
	public static Car createAudi(){
		return new Audi();
	}
	
	public static Car createByd(){
		return new Byd();
	}
}
