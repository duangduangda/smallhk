package com.smallhk.core.dp.singleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import org.junit.Test;

public class SingletonDemo1Test {

	@Test
	public void testSingleton() {
		SingletonDemo1 s1 = SingletonDemo1.getInstance();
		SingletonDemo1 s2 = SingletonDemo1.getInstance();
		assertEquals(s1, s2);
	}
	
	//反射破解单例模式-解决方案可以在构造函数中抛出异常
	@SuppressWarnings("unchecked")
	@Test
	public void testReflectionSingleton() throws Exception{
		Class<SingletonDemo1> clazz = (Class<SingletonDemo1>) Class.forName("com.smallhk.dp.singleton.SingletonDemo1");
		Constructor<SingletonDemo1> c = clazz.getDeclaredConstructor(null);
		c.setAccessible(true);
		
		SingletonDemo1 s3 = c.newInstance();
		SingletonDemo1 s4 = c.newInstance();
		
		assertFalse(s3 == s4);
	}
	
	@Test
	public void  testSerializeSingleton() throws Exception{
		SingletonDemo1 s5 = SingletonDemo1.getInstance();
		FileOutputStream fos = new FileOutputStream(new File("f:a.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s5);
		oos.close();
		fos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:a.txt"));
		SingletonDemo1 s6 = (SingletonDemo1)ois.readObject();
		
		assertTrue(s5 == s6);
		
	}
	
	

}
