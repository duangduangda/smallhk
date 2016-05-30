package com.smallhk.core;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SerializableTest {

	@Test
	public void testSingleton() {
		Singleton s1 = Singleton.getInstance("wolf");
		Singleton s2 = null;
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("a.bin"));
			ois = new ObjectInputStream(new FileInputStream("a.bin"));
			
			// serialize the object
			oos.writeObject(s1);
			oos.flush();
			
			//deserialize the object
			s2 = (Singleton)ois.readObject();
			
			//if the class Singleton does not contain the method named of readResolve()
			// the next two assertion will return fale
			assertTrue(s1.equals(s2));
			assertTrue(s1 == s2);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWolf(){
		Wolf w1 = new Wolf("grey wolf");
		Wolf w2 = null;
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("b.bin"));
			ois = new ObjectInputStream(new FileInputStream("b.bin"));
			
			oos.writeObject(w1);
			oos.flush();
			
			w2 = (Wolf)ois.readObject();
			
			assertTrue(w1.equals(w2));
			assertFalse(w1 == w2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
