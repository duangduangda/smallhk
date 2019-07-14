package org.dean.duck.core.ds;

import org.dean.duck.core.ds.sort.DataSorter;
import org.junit.Test;

public class TempTest {

	private int []a = {231,45,22,21,212,11};
	
	@Test
	public void testBuildMaxHeap(){
		DataSorter sorter = new DataSorter();
		sorter.buildMaxHeap(a);
	}
	
	@Test
	public void testQSort(){
		qsort(a,0,a.length - 1);
	}
	
	private void qsort(int[] a, int left, int right) {
		if(left < right){
			int privokey = partition(a,left,right);
			qsort(a,left,privokey);
			qsort(a,privokey + 1,right);
		}
	}

	private int partition(int[] a, int left, int right) {
		int key = a[left];
		while(left < right){
			while(left < right && a[right] < key){
				right--;
			}
			
			a[left] = a[right];
			
			while(left < right && a[left] > key){
				left++;
			}
			
			a[right] = a[left];
		}
		a[left] = key;
		outputArr(a);
		return left;
	}

	@Test
	public void testShellSort(){
		shellSort(a);
	}
	
	private void shellSort(int[] a) {
		for(int pace = a.length / 2;pace > 0;pace /= 2){
			for(int i = 0;i < pace;i++){
				for(int j = i + pace;j < a.length;j += pace){
					if(a[j] < a[j - pace]){
						int key = a[j];
						int k = j - pace;
						while(k >= 0 && a[k] > key){
							a[k + pace] = a[k];
							k -= pace;
						}
						a[k + pace] = key;
						outputArr(a);
					}
				}
			}
		}
	}

	@Test
	public void testMergeSort() {
		mergeSort(a,0,a.length - 1);
	}

	private void mergeSort(int[] a, int left, int right) {
		if(left < right){
			int mid = (left + right) / 2;
			mergeSort(a, left, mid);
			mergeSort(a,mid + 1,right);
			merge(a,left,mid,right);
		}
	}

	private void merge(int[] a, int left, int mid, int right) {
		int start = left,center = mid + 1,tempIndex = left;
		int []temp = new int[a.length];
		while(left <= mid && center <= right){
			if(a[left] <= a[center]){
				temp[tempIndex++] = a[left++];
			}else{
				temp[tempIndex++] = a[center++];
			}
			
		}
	
		while(left <= mid){
			temp[tempIndex++] = a[left++];
		}
		
		while(center <= right){
			temp[tempIndex++] = a[center++];
		}
		
//		System.arraycopy(temp,start, a, start, right - start + 1);
		while(start <= right){
			a[start] = temp[start++];
		}
		
		outputArr(a);
	}

	private void outputArr(int[] a) {
		for(int i = 0;i < a.length;i++){
			System.out.print(a[i] + "	");
		}
		System.out.println();
	}
	
	@Test
	public void testShellsort(){
		for(int p = a.length / 2;p > 0;p /= 2){
			 for(int i = 0;i < p;i++){
				 for(int j = i + p;j < a.length;j += p){
					 if(a[j] < a[j - p]){
						 int key = a[j];
						 int k = j - p;
						 while(k >= 0 && a[k] > key){
							 a[k + p] = a[k];
							 k -= p;
						 }
						 a[k + p] = key;
						 outputArr(a);
					 }
				 }
			 }
		}
	}

}
