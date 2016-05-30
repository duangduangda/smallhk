package com.smallhk.core.ds;

/**
 * 
 * @description 求子数组的最大和
 * @author dongyh
 * @since 2016年5月25日 上午9:28:19
 * @version 1.0
 *
 */
public class FindGreatestSubSum {

	public static void main(String[] args) {
		int a[]={1, -2, 3, 10, -4, 7, 2, -5};
		int greatestSubSum = findGreatestSubSum(a);
		System.out.println(greatestSubSum);
	}
	
	public static int findGreatestSubSum(int []a){
		int curSum = 0;
		int maxSum = 0;
		for(int i = 0;i < a.length;i++){
			curSum += a[i];
			if(curSum < 0){
				curSum = 0;//放弃之前的计算，重新开始
			}
			
			if(curSum > maxSum){
				maxSum = curSum;
			}
		}
		
		if(maxSum == 0){ //如果全是负数，则输出最大元素
			maxSum = a[0];
			for(int i = 1;i < a.length;i++){
				if(a[i] > maxSum){
					maxSum = a[i];
				}
			}
		}
		return maxSum;
	}

}
