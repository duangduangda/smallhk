package org.dean.duck.core.ds;

/**
 * @description 翻转句子中单词的顺序，单词内字符的顺序也要改变，句子中单词以空格符隔开
 * @author eric
 * @since 2016年5月25日 上午9:44:15
 * @version 1.0
 *
 */
public class ReverseString {

	public static void main(String[] args) {
		String s = "Hello World";
		char []ch = s.toCharArray(); 
		reverseWord(ch);
		for(int i = 0;i < ch.length;i++){
			System.out.print(ch[i]);
		}
	}

	private static void reverseWord(char[] ch) {
		int len = ch.length;
		//翻转整个句子
		swapStr(ch,0,len - 1);
		
		int s = 0,e = 0;
		//翻转每个单词
		for(int i = 0;i < len;i++){
			e = i;
			if(ch[e] == '\0'){
				//ch[e]为空格，范围从s,e-1
				swapStr(ch,s,e-1);
				s = e + 1;
			}
		}
	}

	public static void swapStr(char []ch,int left,int right){
		int low = left;
		int high = right;
		
		while(low < high){
			swap(ch,low,high);
			low++;
			high--;
		}
		
	}
	
	public static void swap(char []a,int x,int y){
		char temp = a[x];
		a[x] = a[y];
		a[y] = temp;
		
	}
}
