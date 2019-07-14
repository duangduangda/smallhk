package org.dean.duck.core.ds.tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

	private TreeNode root = null;
	
	@SuppressWarnings("unused")
	private List<TreeNode> nodeList = new ArrayList<TreeNode>();
	
	public boolean isEmpty(){
		return null == root;
	}
	
	public void delete(int key) throws Exception{
		TreeNode delNode = this.search(key);
		if(null == delNode){
			throw new Exception("the key does not exists in the binary tree");
		}
		delete(delNode);
	}
	
	public void delete(TreeNode delNode) throws Exception{
		if(null == delNode) return;
		
		//the deleted node neither has a left child node ,nor has a right child node 
		if(null == delNode.leftChild && null == delNode.rightChild){//leaf node
			TreeNode pNode = delNode.parent;
			
			if(delNode == pNode.leftChild){
				pNode.leftChild = null;
			}else{
				pNode.rightChild = null;
			}
			return;
		}
		
		//the deleted node only has a right child node 
		if(null == delNode.leftChild && null != delNode.rightChild){
			TreeNode pNode = delNode.parent;
			
			if(delNode == pNode.leftChild){
				pNode.leftChild = delNode.rightChild;
			}else{
				pNode.rightChild = delNode.rightChild;
			}
			delNode.rightChild.parent = pNode; 
		}
		
		//the deleted node only has a left child node
		if(null != delNode.leftChild && null == delNode.rightChild){
			TreeNode pNode = delNode.parent;
			
			if(delNode == pNode.leftChild){
				pNode.leftChild = delNode.leftChild;
			}else{
				pNode.rightChild = delNode.leftChild;
			}
			delNode.leftChild.parent = pNode;
		}
		
		if(null != delNode.leftChild && null != delNode.rightChild){
			TreeNode successorNode = successor(delNode);
			delete(successorNode);
			delNode.key = successorNode.key;
		}
			
	} 
	 
	//get the next node by LDR travel
	private TreeNode successor(TreeNode node) throws Exception{
		if(null == node){
			return null;
		}
		
		if(null != node.rightChild){
			return minElementNode(node.rightChild);
		}
		
		TreeNode pNode = node.parent;
		while(null != pNode && node == pNode.rightChild){
			node = pNode;
			pNode = pNode.parent;
		}
		return pNode;
		
	}
	
	//get the pre node by LDR travel
	@SuppressWarnings("unused")
	private TreeNode precessor(TreeNode node) throws Exception{
		if(null == node){
			return null;
		}
		
		if(null != node.leftChild){
			return maxElementNode(node.leftChild);
		}
		
		TreeNode pNode = node.parent;
		while(null != pNode && node == pNode.leftChild){
			node = pNode;
			pNode = pNode.parent;
		}
		return pNode;
		
	}

	//find the min key value in the binary tree
	private TreeNode minElementNode(TreeNode node) throws Exception{
		if(null == node){
			throw new Exception("the tree is empty");
		}
		
		TreeNode pNode = node;
		while(null != pNode.leftChild){
			pNode = pNode.leftChild;
		}
		return pNode;
	}
	
	//find the max key value in the binary tree
	private TreeNode maxElementNode(TreeNode node) throws Exception{
		if(null == node){
			throw new Exception("the tree is empty");
		}
		
		TreeNode pNode = node;
		while(null != pNode.rightChild){
			pNode = pNode.rightChild;
		}
		return pNode;
	}

	public void insert(int key){
		TreeNode parentNode = null;
		TreeNode newNode = new TreeNode(key, null, null, null);
		TreeNode pNode = root;
		
		if(null == root){
			root = newNode;
			return;
		}
		
		while(null != pNode){
			parentNode = pNode;
			if(key < pNode.key){
				pNode = pNode.leftChild;
			}else if(key > pNode.key){
				pNode = pNode.rightChild;
			}else{
				return;
			}
		}
		
		if(key < parentNode.key){
			parentNode.leftChild = newNode;
		}else{
			parentNode.rightChild = newNode;
		}
		newNode.parent = parentNode;
	}
	
	public TreeNode search(int key){
		TreeNode pNode = root;
		
		while(pNode != null && pNode.key != key){
			if(key < pNode.key){
				pNode = pNode.leftChild;
			}else{
				pNode = pNode.rightChild;
			}
		}
		
		return pNode;
	}
	

	@SuppressWarnings("unused")
	private class TreeNode{
		private int key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;
		
		
		public TreeNode(int key,TreeNode leftChild,TreeNode rightChild,TreeNode parent){
			this.key = key;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
		}
		
		public int getKey(){
			return this.key;
		}
		
		public String toString(){
	        String leftkey = (leftChild == null ? "" : String  
                    .valueOf(leftChild.key));  
            String rightkey = (rightChild == null ? "" : String  
                    .valueOf(rightChild.key));  
            return "(" + leftkey + " , " + key + " , " + rightkey + ")";  
		}
	}
}
