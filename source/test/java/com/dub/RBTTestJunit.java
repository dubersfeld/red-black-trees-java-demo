package com.dub;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dub.site.redBlackTrees.Node;
import com.dub.site.redBlackTrees.SimpleNode;
import com.dub.site.redBlackTrees.SimpleNodeFactory;
import com.dub.site.redBlackTrees.Tree;

public class RBTTestJunit {

	@Test
	public void testAdd() {
		
		SimpleNodeFactory nodeFactory = new SimpleNodeFactory();
		
		Tree<String, SimpleNodeFactory> tree = new Tree<>(nodeFactory);
		
		SimpleNode n1 = nodeFactory.build(40, Node.Color.RED);
		SimpleNode n2 = nodeFactory.build(10, Node.Color.RED);
		SimpleNode n3 = nodeFactory.build(27, Node.Color.RED);
		SimpleNode n4 = nodeFactory.build(63, Node.Color.RED);
		SimpleNode n5 = nodeFactory.build(79, Node.Color.RED);
		SimpleNode n6 = nodeFactory.build(93, Node.Color.RED);
		SimpleNode n7 = nodeFactory.build(70, Node.Color.RED);
		SimpleNode n8 = nodeFactory.build(94, Node.Color.RED);
		SimpleNode n9 = nodeFactory.build(19, Node.Color.RED);
		SimpleNode n10 = nodeFactory.build(3, Node.Color.RED);
		
		tree.insert(n1);
		tree.insert(n2);
		tree.insert(n3);
		tree.insert(n4);
		tree.insert(n5);
		tree.insert(n6);
		tree.insert(n7);
		tree.insert(n8);
		tree.insert(n9);
		tree.insert(n10);
		
		assertEquals(63, tree.getRoot().getKey());
		
		assertEquals(27, tree.getRoot().getLeft().getKey());
		
		assertEquals(n6,tree.search(tree.getRoot(), 93));
		
		assertEquals(n8,tree.search(tree.getRoot(), 93).getRight());
		
		assertEquals(tree.getNil(),tree.search(tree.getRoot(), 93).getLeft());
		
		assertEquals(79,tree.getRoot().getRight().getKey());
		
		assertEquals(3,n2.getLeft().getKey());
		
		assertEquals(19,n2.getRight().getKey());
											
		String refList = 
		"[3 RED, 10 BLACK, 19 RED, 27 RED, 40 BLACK, 63 BLACK, 70 BLACK, 79 RED, 93 BLACK, 94 RED]";
			
		assertEquals(refList, tree.inOrderWalk(tree.getRoot()).toString());
		
		Tree<String, SimpleNodeFactory> tree2 
						= new Tree<>(tree, nodeFactory);	

		assertEquals(refList,tree2.inOrderWalk(tree2.getRoot()).toString());
		
		tree.remove(n3);
		
		String refListA = 
		"[3 BLACK, 10 RED, 19 RED, 40 BLACK, 63 BLACK, 70 BLACK, 79 RED, 93 BLACK, 94 RED]";
				
		assertEquals(refListA,tree.inOrderWalk(tree.getRoot()).toString());
		
		assertEquals(refList,tree2.inOrderWalk(tree2.getRoot()).toString());
		
   }
}