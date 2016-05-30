package com.smallhk.core.ds;

/**
 * 
 * @description 在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
 * @author dongyh
 * @since 2016年5月25日 上午11:06:06
 * @version 1.0
 *
 */
public class FindFirstOnce {

	public static void main(String[] args) {
		String s = "abaccdeff";
		char c = findFirstOnce(s);
		System.out.println(c);
	}

	private static char findFirstOnce(String str) {
		char []ch = str.toCharArray();
		int []counter = new int[256];
		for(int i = 0;i < str.length();i++){
			counter[ch[i]]++;
		}
		for(int i = 0;i < str.length();i++){
			if(counter[ch[i]] == 1){
				return ch[i];
			}
		}
		return '\0';
	}

}
