package com.dub.spring.redBlackTrees;

import java.io.Serializable;

/** T is the Node payload */
public interface Node<T extends Serializable> {


	public int getKey();

	public void setKey(int key);
	
	public Node<T> getLeft();

	public void setLeft(Node<T> left);

	public Node<T> getRight();

	public void setRight(Node<T> right);

	public Node<T> getParent();

	public void setParent(Node<T> parent);
	
	public T getData();
	
	public void setData(T data);
	
	public Color getColor();
	
	public void setColor(Color color);
	
	
	
	public static enum Color {
		RED, BLACK
	}
	  
}
