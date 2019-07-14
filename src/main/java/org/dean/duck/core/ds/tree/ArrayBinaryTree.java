package org.dean.duck.core.ds.tree;

public class ArrayBinaryTree<T> {
	
	private static int DEFAULT_DEEP = 8;
	private int deep;
	private int arraySize;
	private Object[] datas;
	
	
	public ArrayBinaryTree(){
		this.deep = DEFAULT_DEEP;
		this.arraySize = (int)Math.pow(2, this.deep);
		this.datas = new Object[this.arraySize];
	}
	
	public ArrayBinaryTree(int deep){
		this.deep = deep;
		this.arraySize = (int)Math.pow(2, this.deep);
		this.datas = new Object[this.arraySize];
	}
	
	public ArrayBinaryTree(int deep,Object data){
		this.deep = deep;
		this.arraySize = (int)Math.pow(2, this.deep);
		this.datas = new Object[this.arraySize];
		this.datas[0] = data;
	}

	/**
	 * @description add node to tree
	 * @param index
	 * @param data
	 * @param left
	 */
	public void addNode(int index, Object data, boolean left) {
		if(null == datas[index]){
			throw new RuntimeException("current node is null,can not add sub node");
		}
		
		if(2 * index + 1 >= this.arraySize){
			throw new RuntimeException("statckoverflow");
		}
		
		if(left){
			datas[2 * index + 1] = data;
		}else{
			datas[2 * index + 2] = data;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T left(int index){
		if(2 * index + 1 >= this.arraySize){
			throw new RuntimeException("current node is a leaf node,do not has a sub node");
		}
		return (T)datas[2 * index + 1];
	}
	
	@SuppressWarnings("unchecked")
	public T right(int index){
		if(2 * index + 1 >= this.arraySize){
			throw new RuntimeException("current node is a leaf node,do not has a sub node");
		}
		return (T)datas[2 * index + 2];
	}
	
	@SuppressWarnings("unchecked")
	public T parent(int index){
		return (T)datas[(index - 1) / 2];
	}
	
	@SuppressWarnings("unchecked")
	public T root(){
		return (T)datas[0];
	}
	
	public int deep(){
		return this.deep;
	}
	
	public int pos(T data){
		for(int i = 0;i < this.arraySize;i++){
			if(datas[i] == data){
				return i;
			}
		}
		return -1;
	}
	
	public String toString(){
		return java.util.Arrays.toString(this.datas);
	}

}
