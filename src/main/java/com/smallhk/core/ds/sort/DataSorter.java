package com.smallhk.core.ds.sort;

public class DataSorter implements Sorter{

	/**
	 * 输出排序结果
	 * 
	 * @param a
	 */
	private void outputArrSortResult(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();

	}

	/**
	 * 交换
	 * 
	 * @param a
	 * @param x
	 * @param y
	 */
	private void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	/**
	 * 冒泡排序：每趟排序结束后，总能找出当前的最大值
	 * 
	 * @param a
	 */
	public void bubbleSort(int[] a) {
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j + 1] < a[j]) {
					swap(a, j, j + 1);
				}
			}
			outputArrSortResult(a);
		}
	}

	/**
	 * 选择排序：设置临时的最值位置，默认为第一个，每次都从带排序的序列中找出最值
	 * 如果当前找到的最小值的位置与排序前设定的默认本轮排序的最值位置不一致，进行交换 否则当前记为本次排序最值
	 * 
	 * @param a
	 */
	public void selectionSort(int[] a) {
		int j, k;
		for (int i = 0; i < a.length; i++) {
			k = i;
			for (j = i + 1; j < a.length; j++) {
				if (a[j] < a[k]) {
					k = j;
				}
			}
			if (i != k) {
				swap(a, i, k);
			}
			outputArrSortResult(a);
		}
	}

	/**
	 * 直接插入排序：从待排序序列中取值后与已排序的序列进行逐一比较（自后向前），
	 * 如果出现比当前要插入的值小，则移动位置，最后将要插入的值放入空出的位置上
	 * 
	 * @param a
	 */
	public void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && key < a[j]) {
				a[j + 1] = a[j]; // 移动记录
				j--;
			}
			a[j + 1] = key;
			outputArrSortResult(a);
		}
	}

	public void shellSort1(int[] a) {
		for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
			for (int i = 0; i < gap; i++) {
				for (int j = i + gap; j < a.length; j = j + gap) {
					if (a[j] < a[j - gap]) {
						int key = a[j];
						int k = j - gap;
						while (k >= 0 && key < a[k]) {
							a[k + gap] = a[k];
							k = k - gap;
						}
						a[k + gap] = key;
					}
					outputArrSortResult(a);
				}
			}
		}
	}

	public void shellSort2(int[] a) {
		for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
			for (int j = gap; j < a.length; j++) {
				if (a[j] < a[j - gap]) {
					int key = a[j];
					int k = j - gap;
					while (k >= 0 && key < a[k]) {
						a[k + gap] = a[k];
						k = k - gap;
					}
					a[k + gap] = key;
				}
				outputArrSortResult(a);
			}

		}
	}

	/**
	 * 希尔排序：设置不同步幅的直接插入排序
	 * 
	 * @param a
	 */
	public void shellSort(int[] a) {
		for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
			for (int j = gap; j < a.length; j++) {
				for (int k = j - gap; k >= 0 && a[k + gap] < a[k]; k = k - gap) {
					swap(a, k, k + gap);
				}
				outputArrSortResult(a);
			}

		}
	}

	/**
	 * 快速排序： 1.冒泡排序的改进版，使用了算法设计的分治思想 2.每趟排序返回最后放置关键字的位置
	 * 3.每趟排序结果：关键字左边的数值比关键之小，关键字右边的数值比关键字大
	 * 
	 * @param a
	 */
	public void quickSort(int[] a) {
		doQuicksort(a, 0, a.length - 1);
	}

	private void doQuicksort(int[] a, int left, int right) {
		if (left < right) {// 必须符合left<right条件才继续进行递归，否则数组越界
			int i = left, j = right;
			int key = a[left];
			while (i < j) {
				while (i < j && a[j] > key) {
					j--;
				}
				if (i < j) {
					a[i++] = a[j];
				}
				while (i < j && a[i] < key) {
					i++;
				}
				if (i < j) {
					a[j--] = a[i];
				}
			}
			a[i] = key;
			outputArrSortResult(a);
			doQuicksort(a, left, i - 1);
			doQuicksort(a, i + 1, right);
		}
	}

	/**
	 * 归并排序：采用了分治思想，将已排序的序列进行合并，得到完整的排序序列 合并时，需要建立和待排序序列一样长度的临时序列，同时使用中点进行二分分治
	 * 合并的末尾，将保存在临时序列中的已排序序列拷贝到原序列中
	 * 
	 * @param a
	 */
	public void mergeSort(int[] a) {
		doMergeSort(a, 0, a.length - 1);
	}

	private void doMergeSort(int[] a, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			doMergeSort(a, left, center);
			doMergeSort(a, center + 1, right);
			merge(a, left, center, right);
		}
	}

	private void merge(int[] a, int left, int center, int right) {
		int[] tempArr = new int[a.length];
		int mid = center + 1;
		int tmpIndex = left;
		int start = left;
		while (left <= center && mid <= right) {
			if (a[left] <= a[mid]) {
				tempArr[tmpIndex++] = a[left++];
			} else {
				tempArr[tmpIndex++] = a[mid++];
			}
		}

		while (left <= center) {
			tempArr[tmpIndex++] = a[left++];
		}

		while (mid <= right) {
			tempArr[tmpIndex++] = a[mid++];
		}

		// TODO复制数组
		// while(start <= right){
		// a[start] = tempArr[start++];
		// }
		System.arraycopy(tempArr, start, a, start, right - start + 1);
		outputArrSortResult(a);
	}

	/**
	 * 堆排序： 1.最重要的两个操作就是构建初始堆和调整堆，而且构建初始堆的过程事实上就是调整堆的过程 2.分治思想的应用
	 * 
	 * @param a
	 */
	public void heapSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		buildMaxHeap(a);
		for (int i = a.length - 1; i >= 1; i--) {
			swap(a, 0, i);
			heapAjust(a, i, 0);
		}
	}

	/**
	 * 调整堆
	 * 
	 * @param a
	 * @param heapSize
	 * @param index
	 */
	private void heapAjust(int[] a, int heapSize, int index) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;

		int largest = index;
		//进入左子树
		if (left < heapSize && a[left] > a[index]) {
			largest = left;
		}

		//进入右子树
		if (right < heapSize && a[right] > a[largest]) {
			largest = right;
		}

		
		if (index != largest) {
			swap(a,index, largest);
			heapAjust(a, heapSize, largest);
		}

	}

	/**
	 * 构建初始大顶堆
	 * 
	 * @param a
	 */
	public void buildMaxHeap(int[] a) {
		for (int i = a.length / 2; i >= 0; i--) {
			heapAjust(a, a.length, i);
		}
		outputArrSortResult(a);
	}
}