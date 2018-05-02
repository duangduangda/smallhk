package com.smallhk.core.collections;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.smallhk.core.collections.Name;
import com.smallhk.core.collections.Name2;

public class HashSetTest {

	@Test
	public void testAdd1() {
		Set<Name>s = new HashSet<Name>();
		s.add(new Name("abc","123"));
		//TODO 如果只是重写了equals,没有重写hashCode,执行返回false
//		assertFalse(s.contains(new Name("abc","123")));
		//TODO 同时覆盖重写equals和hashCode
		assertTrue(s.contains(new Name("abc","123")));
	}
	
	@Test
	public void testAdd2(){
		Set<Name> s = new HashSet<Name>();
		s.add(new Name("abc","123"));
		s.add(new Name("abc","123"));
		assertEquals(1,s.size());
	}
	
	@Test
	public void testAdd3(){
		Set<Name2>s = new HashSet<Name2>();
		s.add(new Name2("abc","123"));
		s.add(new Name2("abc","456"));
		System.out.println(s);
	}

}
