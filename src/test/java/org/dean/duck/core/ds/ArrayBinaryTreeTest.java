package org.dean.duck.core.ds;

import static org.junit.Assert.*;

import org.dean.duck.core.ds.tree.ArrayBinaryTree;
import org.junit.Test;

public class ArrayBinaryTreeTest {

	@Test
	public void test() {
		ArrayBinaryTree<String> abt = new ArrayBinaryTree<String>(4,"ROOT");
		abt.addNode(0,"SECOND",false);
		abt.addNode(2,"THIRD",false);
		abt.addNode(6,"FORTH",false);
		System.out.println(abt);
		assertEquals(4, abt.deep());
		assertNull(abt.left(2));
		assertNotNull(abt.right(2));
		assertEquals("THIRD",abt.right(2));
	}

}
