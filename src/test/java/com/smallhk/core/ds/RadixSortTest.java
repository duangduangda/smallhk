package com.smallhk.core.ds;

import java.util.Arrays;

public class RadixSortTest {
	public static void main(String[] args) {
		int[] a = { 2, 343, 342, 1, 123, 43, 4343, 433, 687, 654, 3 };
		System.out.println("排序前的数组：");  
		outputArr(a);
		radixSort1(a,10,4);
        System.out.println("排序后的数组：");  
        outputArr(a);
	}

	private static void outputArr(int[] a) {
		for(int i = 0;i < a.length;i++){
			System.out.print(a[i] + "	");
		}
		System.out.println();
	}

	private static void radixSort1(int[] data,int radix,int d) {
		 // 缓存数组  
        int[] tmp = new int[data.length];  
        // buckets用于记录待排序元素的信息  
        // buckets数组定义了max-min个桶  
        int[] buckets = new int[radix];  
  
        for (int i = 0, rate = 1; i < d; i++) {  
  
            // 重置count数组，开始统计下一个关键字  
            Arrays.fill(buckets, 0);  
            // 将data中的元素完全复制到tmp数组中  
            System.arraycopy(data, 0, tmp, 0, data.length);  
  
            // 计算每个待排序数据的子关键字  
            for (int j = 0; j < data.length; j++) {  
                int subKey = (tmp[j] / rate) % radix;  
                buckets[subKey]++;  
            }  
  
            for (int j = 1; j < radix; j++) {  
                buckets[j] = buckets[j] + buckets[j - 1];  
            }  
  
            // 按子关键字对指定的数据进行排序  
            for (int m = data.length - 1; m >= 0; m--) {  
                int subKey = (tmp[m] / rate) % radix;  
                data[--buckets[subKey]] = tmp[m];  
            }  
            rate *= radix;  
        }  
	}
	
	/**
	 * 
	 * @param number待排序数组
	 * @param d 表示最大的数有多少位
	 */
	public void radixSort( int []number,int d){
		int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][]temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9,第二维表示该维
        int[]order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while(m <= d){
            for(int i = 0; i < number.length; i++){
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            
            for(int i = 0; i < 10; i++){
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++)
                    {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
	}
}
