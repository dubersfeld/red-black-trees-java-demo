package com.dub;

import com.dub.site.redBlackTrees.Node;
import com.dub.site.redBlackTrees.SimpleNode;
import com.dub.site.redBlackTrees.SimpleNodeFactory;
import com.dub.site.redBlackTrees.Tree;

public class RBTTest {
	
	public static void main(String[] args) {
	  
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
		
		
		System.out.println("main before copyTree");
		
		Tree<String, SimpleNodeFactory> tree2 
		= new Tree<>(tree, nodeFactory);
		
		System.out.println("main after copyTree");
		
		System.out.println("visit tree2");
		tree2.inOrderWalk(tree2.getRoot());
		
		System.out.println("tree.search(42) " 
					+ (tree.search(tree.getRoot(), 42) == tree.getNil()));
		
		System.out.println("tree.search(93).left " 
					+ (tree.search(tree.getRoot(), 93).getLeft() == tree.getNil()));
		
		System.out.println("before remove");
		System.out.println(tree.inOrderWalk(tree.getRoot()).toString());
		
		tree.remove(n3);
		System.out.println("after remove");
		System.out.println(tree.inOrderWalk(tree.getRoot()).toString());
		
		
		System.out.println("visit tree");
		tree.inOrderWalk(tree.getRoot());
		
		System.out.println();
		System.out.println("visit tree2");
		tree2.inOrderWalk(tree2.getRoot());
		

		
		System.out.println("main completed");
	
	}
	
}