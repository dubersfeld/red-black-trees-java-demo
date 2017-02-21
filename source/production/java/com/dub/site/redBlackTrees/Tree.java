package com.dub.site.redBlackTrees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Tree<T extends Serializable, S extends NodeFactory<T>> implements Serializable {

	/**
	 * This class is a generic Red Black Tree with the usual methods search, insert and remove.
	 * It uses the custom generic class Node<T>
	 */
	private static final long serialVersionUID = 1L;
	
	protected Node<T> root;// root node
	protected Node<T> nil;// sentinel node
	protected S nodeFactory;// nodeFactory
	  
    public Tree(Node<T> root, S nodeFactory) {
    	this.root = root;
    	this.nodeFactory = nodeFactory;
    	
    }
    
    public Tree(S nodeFactory) {
    	this.nil = nodeFactory.build(-1, Node.Color.BLACK);
    	this.root = nil;
    	this.nodeFactory = nodeFactory;
    }
    
    public Tree(Tree<T,S> rhs, S nodeFactory) {
    	this.nil = nodeFactory.build(-1, Node.Color.BLACK);
    	copyTree(rhs, nodeFactory);
    }
    
	public Node<T> getNil() {
		return nil;
	}

	public void setNil(Node<T> nil) {
		this.nil = nil;
	}

	public void copyTree(Tree<T,S> rhs, S nodeFactory) {
    	// deep copy method, breadthFirstWalk based
    	Queue<Node<T>> queue = new Queue<>();// rhs side Node not T, always code against interface or abstract class
    	Node<T> x, last;//rhs side
             
    	Queue<Node<T>> queueP = new Queue<>();// this side
    	Node<T> prevC;// this side
    	Node<T> newnode; 

    	if (rhs.root == rhs.nil) {
    		System.out.println("copyTree empty tree");
    		this.root = this.nil;
    		return;// rhs tree is empty
    	}
        	
    	// initial push_back to start

    	queue.push_back(rhs.root);
              
    	last = rhs.nil;// rhs side
    	prevC = nil;
    	 
    	while (!queue.isEmpty()) {   
    
    		x = queue.pop_front();	
    		
    		newnode = nodeFactory.build(x.getKey(), x.getColor(), x.getData(), nil, nil, nil);    
    			
    		if (x == rhs.root) {// special case                 
    			root = newnode;
    			
    		} else {
    			if (last.getParent() != x.getParent()) {// new parent needed
    			
    				prevC = queueP.pop_front();
    			}                      
    			if (x == x.getParent().getLeft()) {// x is a left child
    				prevC.setLeft(newnode);
    			} else {// x is a right child
    				prevC.setRight(newnode);               
    			}
    			
    			newnode.setParent(prevC);       
    		}  
    		   		
    		if (x.getLeft() != rhs.nil) {
    	    
    			queue.push_back(x.getLeft());     
    		}     
    		if (x.getRight() != rhs.nil) {
    			
    			queue.push_back(x.getRight());
    		}
    		
    		if (x.getLeft() != rhs.nil || x.getRight() != rhs.nil) {

    			queueP.push_back(newnode);
    		}
    		last = x;// rhs side     
    			
    	}// while 
    }
   
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}  
    
	public Node<T> search(Node<T> node, int key) {
        while (node != nil && key != node.getKey()) {
            if (key < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;            
    }// search
    
	public void insert(Node<T> z) { 
		Node<T> y = nil;
        Node<T> x = root;
    
        if (root == nil) { // tree empty
        	System.out.println("tree empty");
            root = z;
            root.setParent(nil);
            root.setLeft(nil);
            root.setRight(nil);
            root.setColor(Node.Color.BLACK);// root is black
            return;
        }
               
        while (x != nil) {// subtree not empty
            y = x;
            if (z.getKey() < x.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }// if                
        } // while
        
        z.setParent(y);
         
        if (z.getKey() < y.getKey()) {
             y.setLeft(z);
        } else {
             y.setRight(z);
        }// if
        z.setLeft(nil);
        z.setRight(nil);
        z.setColor(Node.Color.RED);// always initialize inserted node with Red              
        insertRbtFixup(z);  
    }// insert

    
	public void remove(Node<T> z) {// remove node z from tree without deleting it         
        Node<T> y = z;
        Node<T> x;
        Node.Color y_original_color = y.getColor();
        // special case when root is the last remaining node
        if (z == root) {
            root = nil;
        }
          
        if (z.getLeft() == nil) {// no left child                      
            x = z.getRight();
            transplant(z, z.getRight());              
        } else if (z.getRight() == nil) {// no right child                      
            x = z.getLeft();
            transplant(z, z.getLeft());                 
        } else {// both children                         
            y = minimum(z.getRight());// successor of z             
            y_original_color = y.getColor();
            x = y.getRight();
            if (x.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);                     
            }// if
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());                
        }// if

        if (y_original_color == Node.Color.BLACK) {
            removeRbtFixup(x);
        }
    }// removeRbt
	

    private void removeRbtFixup(Node<T> x) {
        Node<T> w;// pointer to x sibling
        while (x != root && x.getColor() == Node.Color.BLACK) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();// sibling
                if (w.getColor() == Node.Color.RED) {
                    w.setColor(Node.Color.BLACK);
                    x.getParent().setColor(Node.Color.RED); 
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();// x new sibling
                }
                if (w.getLeft().getColor() == Node.Color.BLACK && w.getRight().getColor() == Node.Color.BLACK) {
                    w.setColor(Node.Color.RED);  
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == Node.Color.BLACK) {
                        w.getLeft().setColor(Node.Color.BLACK);
                        w.setColor(Node.Color.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }// if
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Node.Color.BLACK);
                    w.getRight().setColor(Node.Color.BLACK);
                    rotateLeft(x.getParent());
                    x = root;// terminate loop
                }// if

            } else {
                w = x.getParent().getLeft();// sibling
                if (w.getColor() == Node.Color.RED) {
                    w.setColor(Node.Color.BLACK);
                    x.getParent().setColor(Node.Color.RED); 
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();// x new sibling
                }
                if (w.getRight().getColor() == Node.Color.BLACK && w.getLeft().getColor() ==Node.Color.BLACK) {
                    w.setColor(Node.Color.RED);  
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Node.Color.BLACK) {
                        w.getRight().setColor(Node.Color.BLACK);
                        w.setColor(Node.Color.RED);
                        rotateLeft(w);
                        w = x.getParent().getRight();
                    }// if
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Node.Color.BLACK);
                    w.getLeft().setColor(Node.Color.BLACK);
                    rotateRight(x.getParent());
                    x = root;// terminate loop
                }// if
            }// if 
        }// while
        x.setColor(Node.Color.BLACK);
    }
	
 
    private Node<T> minimum(Node<T> pNode) {
       
    	while(pNode.getLeft() != nil) {         
    		pNode = pNode.getLeft();
        }
         
        return pNode; 
    }// minimum
    
 
    private Node<T> maximum(Node<T> pNode) {
    	while(pNode.getRight() != nil) {
    		pNode = pNode.getRight();
    	}
        return pNode; 
    }// maximum
	 
   
    private Node<T> successor(Node<T> pNode) {
    	if (pNode.getRight() != nil) {
    		return minimum(pNode.getRight());
        }
        Node<T> ptr = pNode.getParent();
        while (ptr != nil && pNode == ptr.getRight())  {
             pNode = ptr; ptr = ptr.getParent();
        }
        return ptr;        
    }// successor 
     

    private Node<T> predecessor(Node<T> pNode) { 
    	if (pNode.getLeft() != nil) {
            return maximum(pNode.getLeft());
        }
        Node<T> ptr = pNode.getParent();
        while (ptr != nil && pNode == ptr.getLeft())  {
            pNode = ptr; ptr = ptr.getParent();
        }
        return ptr;        
    }// predecessor
    
    // used for debugging only
    public List<KeyColor> inOrderWalk(Node<T> x) {
    	List<KeyColor> list = new ArrayList<>();
    	Node<T> pNode = minimum(x);
    	System.out.println();
    	while (pNode != nil) {
    		list.add(new KeyColor(pNode.getKey(), pNode.getColor()));
    		System.out.println(pNode.getKey() + " " + pNode.getColor());
    		pNode = successor(pNode);
    	}
    	return list;
    }
          
    /** replace node u by node v in this tree 
      */
	private void transplant(Node<T> u, Node<T> v) 
    {        
    	if (u.getParent() == nil) {
    		root = v;   
    	} else if (u == u.getParent().getLeft()) {
    		u.getParent().setLeft(v);
    	} else {
    		u.getParent().setRight(v);
    	}
          
    	if (v != nil) {    
    		 v.setParent(u.getParent()); 
    	}
    }// transplant
	

	 
	private void insertRbtFixup(Node<T> z) 
	{          
		Node<T> y;
		
        while (z.getParent().getColor() == Node.Color.RED) { 
        
            if (z.getParent() == z.getParent().getParent().getLeft()) { 	
            	y = z.getParent().getParent().getRight();
             
             	
            	if (y.getColor() == Node.Color.RED) {
                	
            		z.getParent().setColor(Node.Color.BLACK);// parent becomes Black
                    y.setColor(Node.Color.BLACK);// uncle becomes Black
                    z.getParent().getParent().setColor(Node.Color.RED);// grandfather becomes Red
                    z = z.getParent().getParent();// move two levels up
            	} else { 
                 
            		if (z == z.getParent().getRight()) {
                         z = z.getParent();
                         rotateLeft(z);
                    }
                    z.getParent().setColor(Node.Color.BLACK);//->parent()->setColor(Node.Color.BLACK);
                    z.getParent().getParent().setColor(Node.Color.RED);//->parent()->parent()->setColor(Node.Color.RED); 
                    rotateRight(z.getParent().getParent());
            	}// if                    
            } else {    
            	y = z.getParent().getParent().getLeft();// left uncle
                if (y.getColor() == Node.Color.RED) {
                	z.getParent().setColor(Node.Color.BLACK);// parent become Black
                    y.setColor(Node.Color.BLACK);
                    z.getParent().getParent().setColor(Node.Color.RED);// grandfather becomes Red
                    z = z.getParent().getParent();// move two levels up   
                } else {
                	if (z == z.getParent().getLeft()) {// z is a left node
                		z = z.getParent();
                        rotateRight(z);
                	}
                    z.getParent().setColor(Node.Color.BLACK);
                    z.getParent().getParent().setColor(Node.Color.RED); 
                    rotateLeft(z.getParent().getParent());
                }// if                    
            }// if               
        }// while
        
        root.setColor(Node.Color.BLACK);
	}//insertRbtFixup
	
	
	// helper method for Red Black Trees	
	private void rotateLeft(Node<T> x) {                  
		Node<T> y = x.getRight();
		x.setRight(y.getLeft());      
		if (y.getLeft() != nil) {            
			y.getLeft().setParent(x);
		}  
		y.setParent(x.getParent());      
		if (x.getParent() == nil) {        
			root = y;        
		} else if (x == x.getParent().getLeft()) {        
			x.getParent().setLeft(y);        
		} else {        
			x.getParent().setRight(y);            
		}// if
		y.setLeft(x);        
		x.setParent(y);        
	}// rotateLeft

    private void rotateRight(Node<T> x) {       
        Node<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != nil) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == nil) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }// if
        y.setRight(x);
        x.setParent(y);
    }// rotateRight
	
}// class
