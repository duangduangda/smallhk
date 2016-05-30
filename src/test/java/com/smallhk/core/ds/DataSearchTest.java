package com.smallhk.core.ds;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smallhk.core.ds.search.DataSearch;
import com.smallhk.core.ds.sort.DataSorter;
import com.smallhk.core.ds.sort.Sorter;

public class DataSearchTest {

	@Test
	public void test() {
		int []a = {2,1,5,6,0};
		
		Sorter sorter = new DataSorter();
		sorter.quickSort(a);
		
		DataSearch search = new DataSearch();
		assertEquals(3,search.binarySearch(a, 5,0,a.length - 1));
		assertEquals(-1,search.binarySearch(a, 4,0,a.length - 1));
	}
	
	@Test
	public void testRecursive(){
		int []a = {32,21,15,16,20};
		
		Sorter sorter = new DataSorter();
		sorter.quickSort(a);
		
		DataSearch search = new DataSearch();
		assertEquals(0,search.recursiveBinarSearch(a, 15,0,a.length - 1));
		assertEquals(-1,search.recursiveBinarSearch(a, 4,0,a.length - 1));
	}
	
	@Test
	public void testBinaryTreeSearch(){
		int []a = {4,1,45,78,345,23,12,3,6,21};
		 
		DataSearch search = new DataSearch();
		assertTrue(search.binaryTreeSearch(a,23));
	}

}
