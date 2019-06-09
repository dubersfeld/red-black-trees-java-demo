package com.dub.spring.redBlackTrees;

import com.dub.spring.redBlackTrees.Geometry;
import com.dub.spring.redBlackTrees.Node;

/** Here Geometry is a helper class used for display on Canvas */
public class DisplayNode implements Node<Geometry> {
	 
	private int key;
	private Node<Geometry> left, right, parent;
	private Geometry data;
	private Node.Color color;
	
	
	public DisplayNode(int key, Node.Color color) {
		this.key = key;
		this.color = color;
		this.data = new Geometry();// not null
    	this.left = null;
    	this.right = null;
    	this.parent = null;     
	}
	

    public DisplayNode(int key, Node.Color color, Geometry data, 
    		Node<Geometry> left, Node<Geometry> right, Node<Geometry> parent) 
    {
    	this.key = key;
    	this.color = color;
    	this.data = new Geometry(data);
    	this.left = left;
    	this.right = right;
    	this.parent = parent;
    }
	
		
    @Override
	public int getKey() {
		return key;
	}


    @Override
	public void setKey(int key) {
		this.key = key;
	}

    @Override
	public Node<Geometry> getLeft() {
		return left;
	}

    @Override
	public void setLeft(Node<Geometry> left) {
		this.left = left;
	}

    @Override
	public Node<Geometry> getRight() {
		return right;
	}

    @Override
	public void setRight(Node<Geometry> right) {
		this.right = right;
	}

    @Override
	public Node<Geometry> getParent() {
		return parent;
	}

    @Override
	public void setParent(Node<Geometry> Parent) {
		this.parent = Parent;
	}

    @Override
	public Geometry getData() {
		return data;
	}

    @Override
	public void setData(Geometry data) {
		this.data = data;
	}


	@Override
	public Node.Color getColor() {
		return color;
	}


	@Override
	public void setColor(Node.Color color) {
		this.color = color;
		
	}
}