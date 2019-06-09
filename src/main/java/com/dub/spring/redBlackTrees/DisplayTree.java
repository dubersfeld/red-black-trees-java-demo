package com.dub.spring.redBlackTrees;

import java.util.ArrayList;
import java.util.List;

public class DisplayTree extends Tree<Geometry, DisplayNodeFactory> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DisplayTree(Node<Geometry> root, DisplayNodeFactory nodeFactory) {
		super(root, nodeFactory);    
	}
  
	public DisplayTree(DisplayNodeFactory nodeFactory) {	
		super(nodeFactory);
		this.nodeFactory = nodeFactory;
	}
	     
	// copy constructor needed
	public DisplayTree(DisplayTree rhs, DisplayNodeFactory nodeFactory) {	
		super(rhs, nodeFactory);
	}
	      
	       
    public boolean breadthFirstWalk(List<List<NodeResult>> results) 
    {
        if (root == nil) {// empty tree
            return true;        
        }
        
        Queue<Node<Geometry>> queue = new Queue<>();
        Node<Geometry> node;
            	
        root.getData().setDepth(0);
        root.getData().setIndex(0); 
              
        queue.push_back(root);// initial push to start loop
        
        results.clear();
           
        for (int i = 0; i < 5; i++) {// depth should be < 5
            results.add(new ArrayList<NodeResult>());
        }
        
        while (!queue.isEmpty()) {        
            node = queue.pop_front();
                
        	// update node depth and index attributes
            if (node.getParent() != nil) {// not root
                node.getData().setDepth( node.getParent().getData().getDepth() + 1 );                     
                if (node.getData().getDepth() > 4) {
                    return false;         
                }    
                if ( node == node.getParent().getLeft() ) {// left child
                    node.getData().setIndex(2 * node.getParent().getData().getIndex());
                } else {// right child
                    node.getData().setIndex(2 * node.getParent().getData().getIndex() + 1);
                }// if
            }// if             
            if (node.getParent() != nil) {// not root
            	
            	//public NodeResult(int key, Node.Color color, int index, int parentIndex) 
            	NodeResult result = new NodeResult(
            									node.getKey(), 
            									node.getColor(), 
            									node.getData().getIndex(), 
            									node.getParent().getData().getIndex()
            						);
            	           	
            	results.get(node.getData().getDepth()).add(result);
            } else {
            	// root       
            	NodeResult result = new NodeResult(
						node.getKey(), 
						node.getColor(), 
						node.getData().getIndex() 
				);
            	
            	results.get(node.getData().getDepth()).add(result);
            }// if    
            
            if (node.getLeft() != nil) {
                queue.push_back(node.getLeft());
            }
            if (node.getRight() != nil) {
                queue.push_back(node.getRight());
            } 
        }// while
        
        return true;    
    }// breadthFirstWalk
     

	/** additional method required for insertion */
	public String checkInsert(int key, List<List<NodeResult>> results) 
	{
        // first search for key
        if (search(root, key) != nil) {// key already present
        	
          	results = new ArrayList<>();
          	return "NP";
        } else {// key not found    	
            // first save a local copy of the tree 
    		DisplayTree treeSave = new DisplayTree(this, nodeFactory);
    	
    		treeSave.inOrderWalk(treeSave.getRoot());// debug only
    		// then insert new key
    	    insert(nodeFactory.build(key, Node.Color.BLACK));
    		boolean allowed = breadthFirstWalk(results);
   
    	    if (!allowed) {
    	    	results = new ArrayList<>();
    	    	// roll back
    	    	this.copyTree(treeSave, nodeFactory); 
                breadthFirstWalk(results);
                return "ND";
    	    } else {
    	    	return "OK";
    	    }
        }
	}
	
	
	/** additional method required for deletion */
	public String checkRemove(int key, List<List<NodeResult>> results) 
	{
		// first search key
		Node<Geometry> node = search(root, key);
		
		if (node == nil) {
			results = new ArrayList<>();
			return "NF";
		} else {
			this.remove(node);
	        breadthFirstWalk(results);// needed for display
	        return "OK";
		}
	} 
	
}// class