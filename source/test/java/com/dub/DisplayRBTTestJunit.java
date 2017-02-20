package com.dub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dub.site.redBlackTrees.DisplayNode;
import com.dub.site.redBlackTrees.DisplayNodeFactory;
import com.dub.site.redBlackTrees.DisplayTree;
import com.dub.site.redBlackTrees.Node;
import com.dub.site.redBlackTrees.NodeResult;

public class DisplayRBTTestJunit {

	@Test
	public void testAdd() {
		
		DisplayNodeFactory nodeFactory = new DisplayNodeFactory();
		
		DisplayTree tree = new DisplayTree(nodeFactory);
		
		DisplayNode n1 = nodeFactory.build(40, Node.Color.RED);
		DisplayNode n2 = nodeFactory.build(10, Node.Color.RED);
		DisplayNode n3 = nodeFactory.build(27, Node.Color.RED);
		DisplayNode n4 = nodeFactory.build(63, Node.Color.RED);
		DisplayNode n5 = nodeFactory.build(79, Node.Color.RED);
		DisplayNode n6 = nodeFactory.build(93, Node.Color.RED);
		DisplayNode n7 = nodeFactory.build(70, Node.Color.RED);
		DisplayNode n8 = nodeFactory.build(94, Node.Color.RED);
		DisplayNode n9 = nodeFactory.build(19, Node.Color.RED);
		DisplayNode n10 = nodeFactory.build(3, Node.Color.RED);
		
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
		
		DisplayTree tree2 
						= new DisplayTree(tree, nodeFactory);	

		assertEquals(refList,tree2.inOrderWalk(tree2.getRoot()).toString());
		
		tree.remove(n3);
		
		String refListA = 
		"[3 BLACK, 10 RED, 19 RED, 40 BLACK, 63 BLACK, 70 BLACK, 79 RED, 93 BLACK, 94 RED]";
				
		assertEquals(refListA,tree.inOrderWalk(tree.getRoot()).toString());
		
		assertEquals(refList,tree2.inOrderWalk(tree2.getRoot()).toString());
	
		String resultsString =
		"[[63 BLACK 0 0], [27 RED 0 0, 79 RED 1 0], [10 BLACK 0 0, 40 BLACK 1 0, 70 BLACK 2 1, 93 BLACK 3 1], [3 RED 0 0, 19 RED 1 0, 94 RED 7 3], []]";
		
		List<List<NodeResult>> results = new ArrayList<List<NodeResult>>();
		
		tree2.breadthFirstWalk(results);
		
		assertEquals(resultsString,results.toString());
		
   }
}