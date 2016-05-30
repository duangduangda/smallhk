package com.smallhk.core.collections;

import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {

	@Test
	public void testPut1() {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		treeMap.put("ccc", 99);
		treeMap.put("ccc", 99);
		treeMap.put("ccc", 99);
		System.out.println(treeMap);
	}

}
