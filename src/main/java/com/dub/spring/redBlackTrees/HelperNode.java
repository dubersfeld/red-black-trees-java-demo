package com.dub.spring.redBlackTrees;


/** A generic queue node T is the Node payload */
public class HelperNode<T> {

	private T data;
	private HelperNode<T> next;
	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public HelperNode<T> getNext() {
		return next;
	}

	public void setNext(HelperNode<T> next) {
		this.next = next;
	}
	
	
	  
}
