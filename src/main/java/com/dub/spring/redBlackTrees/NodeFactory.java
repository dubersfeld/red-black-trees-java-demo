package com.dub.spring.redBlackTrees;

import java.io.Serializable;


public abstract class NodeFactory<T extends Serializable> {
	
	abstract public Node<T> build(int key, Node.Color color);	
	
	abstract public Node<T> build(int key, Node.Color color, T data, Node<T> left, Node<T> right, Node<T> parent);
}

