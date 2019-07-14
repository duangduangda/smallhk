package org.dean.duck.core.ds.tree;

public class TwoLinkBinaryTree<T> {
	private TreeNode root;

	public TwoLinkBinaryTree() {
		root = new TreeNode();
	}

	public TwoLinkBinaryTree(T data) {
		root = new TreeNode(data);
	}

	public static class TreeNode {
		Object data;
		TreeNode left;
		TreeNode right;

		public TreeNode() {
		}

		public TreeNode(Object data) {
			this.data = data;
		}

		public TreeNode(Object data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
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
		return node;
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

}
