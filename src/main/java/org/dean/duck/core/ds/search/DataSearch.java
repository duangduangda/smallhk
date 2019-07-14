package org.dean.duck.core.ds.search;

import org.dean.duck.core.ds.tree.BinarySearchTree;

public class DataSearch {
	
	public int binarySearch(int[] a, int key,int left,int right) {
		while(left < right){
			int mid = (left + right) / 2;
			if(a[mid] > key){
				right = mid - 1;
			}else if(a[mid] < key){
				left = mid + 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	public int recursiveBinarSearch(int []a,int key,int left,int right){
		if(left < right){
			int mid = (left + right) / 2;
			if(a[mid] > key){
				return recursiveBinarSearch(a, key, left, mid - 1);
			}else if(a[mid] < key){
				return recursiveBinarSearch(a, key, mid + 1, right);
			}else{
				return mid;
			}
		}
		return -1;
	}

	public boolean binaryTreeSearch(int[] a,int key) {
		BinarySearchTree tree = createBinaryTree(a);
		return tree.search(key) != null;
	}

	private BinarySearchTree createBinaryTree(int[] a) {
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0;i < a.length;i++){
			tree.insert(a[i]);
		}
		return tree;
	}
}
