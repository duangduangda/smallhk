package com.smallhk.core.ds;

public class HeapSortTest {

	public static void main(String[] args) {
		int []a = {2,1,3,6,4,7,5};
		heapSort(a);
		outputArr(a);
	}

	private static void heapSort(int[] a) {
		buildMaxHeap(a,a.length);
		doHeapSort(a,a.length);
	}

	private static void doHeapSort(int []a,int size) {
		for(int i = size - 1;i >= 0;i--){
			swap(a,i,0);
			heapAjust(a,i,0);
		}
	}

	private static void buildMaxHeap(int []a,int size) {
		for(int i = size - 1;i >= 0;i--){
			heapAjust(a, size, i);
		}
	}

	private static void heapAjust(int[] a, int size, int i) {
		if(i < size){
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int largest = i;
			
			if(left < size && a[left] > a[largest]){
				largest = left;
			}
			
			if(right < size && a[right] > a[largest]){
				largest =right;
			}
			
			if(i != largest){
				swap(a,i,largest);
				heapAjust(a, size, largest);
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void outputArr(int[] a) {
		for(int i = 0;i < a.length;i++){
			System.out.print(a[i] + "	");
		}
		System.out.println();
	}

}
