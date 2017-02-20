package com.dub.site.redBlackTrees;


public class Queue<T> {
	
	/** A generic queue class with sentinel */
	
	private HelperNode<T> nil;// sentinel
	private HelperNode<T> last;// pointer
	
	public Queue() {
		nil = new HelperNode<T>();
		nil.setNext(nil);
		last = nil;
	}
	
	public T pop_front() { 
		if (this.isEmpty()) {
			return null;
		} else if (nil.getNext().getNext() == nil) {
			T data = nil.getNext().getData();
			nil.setNext(nil.getNext().getNext());
			last = nil;
			return data;
		} else {
			T data = nil.getNext().getData();
			nil.setNext(nil.getNext().getNext());
			return data;
		}
	}
	
	public void push_back(T object) {
		HelperNode<T> newNode = new HelperNode<T>();
		newNode.setNext(nil);
		newNode.setData(object);
		last.setNext(newNode);
		last = newNode;
	}
	
	
	/** used fro debugging only */
	public void display() {
		System.out.println(); 
		HelperNode<T> ptr = nil.getNext();
	
		while (ptr != nil) {
			System.out.println(ptr.getData().toString());
			ptr = ptr.getNext();
		}
	}
	
	
	public boolean isEmpty() {
		return (nil.getNext() == nil);
		
	}
	
	public T front() { 
		if (this.isEmpty()) {
			return null;
		} else {
			return nil.getNext().getData();
		}
		
	}

	public HelperNode<T> getNil() {
		return nil;
	}

	public void setNil(HelperNode<T> nil) {
		this.nil = nil;
	}

	public HelperNode<T> getLast() {
		return last;
	}

	public void setLast(HelperNode<T> last) {
		this.last = last;
	}

}
		
