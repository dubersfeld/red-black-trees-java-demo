package com.dub.spring.redBlackTrees;


/** Simple class */
public class SimpleNode implements Node<String> {
	
	private int key;
	private Node<String> left, right, parent;
	private String data;
	private Node.Color color;
     	
	public SimpleNode(int key, Node.Color color) {
		this.key = key;
		this.color = color;
		this.data = "";
    	this.left = null;
    	this.right = null;
    	this.parent = null;     
	}
	
    public SimpleNode(int key, Node.Color color, String data, Node<String> left, Node<String> right, Node<String> parent) {
    	this.key = key;
    	this.color = color;
    	this.data = data;
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
	public Node<String> getLeft() {
		return left;
	}

	@Override
	public void setLeft(Node<String> left) {
		this.left = left;
	}

	@Override
	public Node<String> getRight() {
		return right;
	}

	@Override
	public void setRight(Node<String> right) {
		this.right = right;
	}

	@Override
	public Node<String> getParent() {
		return parent;
	}

	@Override
	public void setParent(Node<String> parent) {
		this.parent = parent;
	}

	@Override
	public Node.Color getColor() {
		return color;
	}

	@Override
	public void setColor(Node.Color color) {
		this.color = color;
		
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

} 