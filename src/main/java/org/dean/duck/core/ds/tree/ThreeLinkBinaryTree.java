package org.dean.duck.core.ds.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ThreeLinkBinaryTree<T> {
	private TreeNode root;

	public ThreeLinkBinaryTree() {
		root = new TreeNode();
	}

	public ThreeLinkBinaryTree(T data) {
		root = new TreeNode(data);
	}

	public static class TreeNode {
		Object data;
		TreeNode left;
		TreeNode right;
		TreeNode parent;

		public TreeNode() {
		}

		public TreeNode(Object data) {
			this.data = data;
		}

		public TreeNode(Object data, TreeNode left, TreeNode right,TreeNode parent) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

	}

	public TreeNode addNode(TreeNode parent, T data, boolean isLeft) {
		if (null == parent) {
			throw new RuntimeException("The given parent node does not exists");
		}

		if (isLeft && parent.left != null) {
			throw new RuntimeException("This node has have left child,can not add to left child");
		}

		if (!isLeft && parent.right != null) {
			throw new RuntimeException("This node has have right child,can not add to right child");
		}

		TreeNode node = new TreeNode(data);
		if (isLeft) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		node.parent = parent;
		return node;
	}

	@SuppressWarnings("unchecked")
	public T parent(TreeNode node){
		if(null == node){
			throw new RuntimeException("This node is null,does not have a parent node");
		}
		
		if(node == root){
			throw new RuntimeException("This node is root node,does not have a parent node");
		}
		return (T)node.parent.data;
	}
	
	public boolean isEmpty(){
		return root.data == null;
	}
	
	public TreeNode root() {
		if(isEmpty()){
			throw new RuntimeException("the root of binary tree is null");
		}
		return this.root;
	}

	@SuppressWarnings("unchecked")
	public T leftChild(TreeNode parent) {
		if(null == parent){
			throw new RuntimeException("the node " + parent + " is null,can not add sub node");
		}
		return parent.left == null?null:(T)parent.left.data;
	}
	
	@SuppressWarnings("unchecked")
	public T rightChild(TreeNode parent) {
		if(null == parent){
			throw new RuntimeException("the node " + parent + " is null,can not add sub node");
		}
		return parent.right == null?null:(T)parent.right.data;
	}
	
	public int deep(){
		return deep(root);
	}
	
	public int deep(TreeNode root){
		if(root == null){
			return 0;
		}
		
		if(root.left == null && root.right == null){
			return 1;
		}else{
			int leftDeep = deep(root.left);
			int rightDeep = deep(root.right);
			int max =  leftDeep >= rightDeep?leftDeep:rightDeep;
			return max + 1;
		}
	}
	
	public void preIterator(TreeNode node){
		System.out.println(node.data);
		
		if(node.left != null){
			preIterator(node.left);
		}
		
		if(node.right != null){
			preIterator(node.right);
		}
	}

	public void inIterator(TreeNode node){
		
		if(node.left != null){
			inIterator(node.left);
		}
		
		System.out.println(node.data);
		
		if(node.right != null){
			inIterator(node.right);
		}
	}
	
	public void postIterator(TreeNode node){
		
		if(node.left != null){
			postIterator(node.left);
		}
		
		if(node.right != null){
			postIterator(node.right);
		}
		
		System.out.println(node.data);
	}
	
	public void breathFirstIterator(){
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		if(this.root != null){
			queue.offer(root);
		}
		
		while(!queue.isEmpty()){
			System.out.println(queue.peek().data);
			
			TreeNode p = queue.poll();
			
			if(p.left != null){
				queue.offer(p.left);
			}
			
			if(p.right != null){
				queue.offer(p.right);
			}
		}
		
	}
}
