package com.smallhk.core.ds;

/**
 * @description methods to handle the given String
 * @author dongyh
 * @since 2016年5月8日 下午5:40:11
 * @version 1.0
 *
 */
public class StringHandler {

	public static String middleReverse(String s) {
		char[]c = s.toCharArray();
		int index = s.indexOf(" ");
		return reverse(c,0,c.length - 1,index);
	}

	private static String reverse(char[] c, int left, int right,int index) {
		if(left < right){
			StringBuffer sb = new StringBuffer();
			sb.append(doReverse(c,left,index - 1));
			sb.append(" ");
			sb.append(doReverse(c,index + 1,right));
			return sb.toString();
		}
		return null;
	}

	public static String doReverse(char[] c, int left, int right) {
		System.out.println("left=" + left + ",right=" + right);
		int start = left,end = right;
		while(left < right){
			swap(c,left,right);
			left++;
			right--;
		} 
		StringBuilder sb = new StringBuilder();
		for(int i = start;i <= end;i++){
			sb.append(c[i]);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	private static void swap(char[] a, int left, int right) {
		char temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

}
