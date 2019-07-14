package org.dean.duck.core.ds;

import org.dean.duck.core.ds.tree.TwoLinkBinaryTree;
import org.junit.Test;

public class TwoLinkBinaryTreeTest {

	@Test
	public void test() {
		TwoLinkBinaryTree<String> binTree = new TwoLinkBinaryTree<String>("Root");
		TwoLinkBinaryTree.TreeNode node1 = binTree.addNode(binTree.root(),"2-left",true);
		TwoLinkBinaryTree.TreeNode node2 = binTree.addNode(binTree.root(),"2-right",false);
		TwoLinkBinaryTree.TreeNode node3 = binTree.addNode(node2,"3-left",true);
		TwoLinkBinaryTree.TreeNode node4 = binTree.addNode(node2,"3-right",false);
		TwoLinkBinaryTree.TreeNode node5 = binTree.addNode(node3,"4-left",true);
		
		System.out.println("2-left:" + binTree.leftChild(node2));
		System.out.println("2-right:" + binTree.rightChild(node2));
		System.out.println(binTree.deep());
	}

}
