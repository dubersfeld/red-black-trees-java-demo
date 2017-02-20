package com.dub;

import java.util.ArrayList;
import java.util.List;

import com.dub.site.redBlackTrees.Node;
import com.dub.site.redBlackTrees.NodeResult;
import com.dub.site.redBlackTrees.DisplayNode;
import com.dub.site.redBlackTrees.DisplayNodeFactory;
import com.dub.site.redBlackTrees.DisplayTree;


public class DisplayRBTTest3 {
	
	public static void main(String[] args) {
		  
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
		tree.inOrderWalk(tree.getRoot());
			
		tree.insert(n2);
		tree.inOrderWalk(tree.getRoot());
				
		tree.insert(n3);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n4);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n5);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n6);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n7);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n8);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n9);
		tree.inOrderWalk(tree.getRoot());
		
		tree.insert(n10);
		tree.inOrderWalk(tree.getRoot());
			
		DisplayTree tree2 = new DisplayTree(tree, nodeFactory);
				
		System.out.println("visit tree2");
		tree2.inOrderWalk(tree2.getRoot());
		
		System.out.println("tree.search(42) " 
					+ (tree.search(tree.getRoot(), 42) == tree.getNil()));
		
		System.out.println("tree.search(63) " 
					+ (tree.search(tree.getRoot(), 63) == tree.getNil()));
		
		tree.remove(n3);
		
		System.out.println("visit tree");
		tree.inOrderWalk(tree.getRoot());
		
		System.out.println();
		System.out.println("visit tree2");
		tree2.inOrderWalk(tree2.getRoot());
		

		List<List<NodeResult>> results = new ArrayList<List<NodeResult>>();

		tree2.breadthFirstWalk(results);
		
		System.out.println(results.toString());
		
		
		System.out.println("main completed");
	
	}
	
}