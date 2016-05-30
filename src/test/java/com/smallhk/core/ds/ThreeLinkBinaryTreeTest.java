package com.smallhk.core.ds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smallhk.core.ds.tree.ThreeLinkBinaryTree;
import com.smallhk.core.ds.tree.ThreeLinkBinaryTree.TreeNode;

public class ThreeLinkBinaryTreeTest {

	@Test
	public void test() {
		ThreeLinkBinaryTree<String> binTree = new ThreeLinkBinaryTree<String>("Root");
		TreeNode node1 = binTree.addNode(binTree.root(), "2-left", true);
		TreeNode node2 = binTree.addNode(binTree.root(), "2-right", false);
		TreeNode node3 = binTree.addNode(node1, "3-left", true);
		TreeNode node4 = binTree.addNode(node1, "3-right", false);
		
		assertEquals(3,binTree.deep());
		assertEquals("2-left",binTree.leftChild(binTree.root()));
		assertEquals("2-right",binTree.rightChild(binTree.root()));
		assertEquals("2-left",binTree.parent(node3));
		
	}
	
	@Test
	public void testBinaryTreeDLRTravel(){
		ThreeLinkBinaryTree<String> binTree = new ThreeLinkBinaryTree<String>("Root");
		TreeNode node1 = binTree.addNode(binTree.root(), "2-left", true);
		TreeNode node2 = binTree.addNode(binTree.root(), "2-right", false);
		TreeNode node3 = binTree.addNode(node1, "3-left", true);
		TreeNode node4 = binTree.addNode(node1, "3-right", false);
		TreeNode node5 = binTree.addNode(node2, "3-right-1", false);
		
		binTree.preIterator(binTree.root());
	}
	

	@Test
	public void testBinaryTreeLDRTravel(){
		ThreeLinkBinaryTree<String> binTree = new ThreeLinkBinaryTree<String>("Root");
		TreeNode node1 = binTree.addNode(binTree.root(), "2-left", true);
		TreeNode node2 = binTree.addNode(binTree.root(), "2-right", false);
		TreeNode node3 = binTree.addNode(node1, "3-left", true);
		TreeNode node4 = binTree.addNode(node1, "3-right", false);
		TreeNode node5 = binTree.addNode(node2, "3-right-1", false);
		
		binTree.inIterator(binTree.root());
	}
	
	@Test
	public void testBinaryTreeLRDTravel(){
		ThreeLinkBinaryTree<String> binTree = new ThreeLinkBinaryTree<String>("Root");
		TreeNode node1 = binTree.addNode(binTree.root(), "2-left", true);
		TreeNode node2 = binTree.addNode(binTree.root(), "2-right", false);
		TreeNode node3 = binTree.addNode(node1, "3-left", true);
		TreeNode node4 = binTree.addNode(node1, "3-right", false);
		TreeNode node5 = binTree.addNode(node2, "3-right-1", false);
		
		binTree.postIterator(binTree.root());
	}
	
	@Test
	public void testBreathFirstTravel(){
		ThreeLinkBinaryTree<String> binTree = new ThreeLinkBinaryTree<String>("Root");
		TreeNode node1 = binTree.addNode(binTree.root(), "2-left", true);
		TreeNode node2 = binTree.addNode(binTree.root(), "2-right", false);
		TreeNode node3 = binTree.addNode(node1, "3-left", true);
		TreeNode node4 = binTree.addNode(node1, "3-right", false);
		TreeNode node5 = binTree.addNode(node2, "3-right-1", false);
		
		binTree.breathFirstIterator();
	}
}
