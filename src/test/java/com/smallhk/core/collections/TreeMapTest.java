package com.smallhk.core.collections;

import java.util.TreeMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeMapTest {

	@Test
	public void testPut1() {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		treeMap.put("ccc", 99);
		treeMap.put("ccc", 99);
		treeMap.put("ccc", 99);
		assertEquals(1, treeMap.size());
		assertEquals(99, treeMap.get("ccc"));
	}

}
