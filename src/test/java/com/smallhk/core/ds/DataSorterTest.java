package com.smallhk.core.ds;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import com.smallhk.core.ds.sort.DataSorter;
import com.smallhk.core.ds.sort.DataSorterHandler;
import com.smallhk.core.ds.sort.Sorter;

public class DataSorterTest {
	private int[] arr = {123,89,676,87,56,43};;
	
	private Sorter proxy = null;
	
	@Before
	public void before(){
		Sorter sorter = new DataSorter();
		DataSorterHandler handler = new DataSorterHandler(sorter);
		proxy = (Sorter) Proxy.newProxyInstance(sorter.getClass().getClassLoader(),
				sorter.getClass().getInterfaces(),
				handler);
	}
	
	@Test
	public void testBubbleSort() {
		proxy.bubbleSort(arr);
		assertResult(arr);
	}

	@Test
	public void testShellSort(){
		proxy.shellSort(arr);
	}
	
	@Test
	public void testSelectionSort(){
		proxy.selectionSort(arr);
		assertResult(arr);
	}
	
	@Test
	public void testQuickSort(){
		proxy.quickSort(arr);
		assertResult(arr);
	}
	
	@Test
	public void testHeapSort(){
		proxy.heapSort(arr);
		assertResult(arr);
	}
	
	@Test
	public void testInsertionSort(){
		proxy.insertionSort(arr);
		assertResult(arr);
	}
	
	@Test
	public void testMergeSort(){
		proxy.mergeSort(arr);
		assertResult(arr);
	}
	
	private void assertResult(int[] result) {
		assertEquals(43,result[0]);
		assertEquals(56,result[1]);
		assertEquals(87,result[2]);
		assertEquals(89,result[3]);
		assertEquals(123,result[4]);
		assertEquals(676,result[5]);
	}

}
