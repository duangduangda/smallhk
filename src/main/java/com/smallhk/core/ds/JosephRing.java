package com.smallhk.core.ds;

public class JosephRing {

	public void handle(int[] a, int p) {
		int k = a.length;
		int i = 0;
		int j = 1;
		while(k > 0){
				if(a[i % a.length] != -1){
					if(j % p == 0){
						System.out.println(a[i % a.length]);
						k--;
						a[i % a.length] = -1;
					}
					j++;
					i++;
				}else{ 
					i++;
				}
		}
	}
}
