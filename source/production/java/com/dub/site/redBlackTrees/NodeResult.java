package com.dub.site.redBlackTrees;

/** This class is used to encapsulate all responses to AJAX requests */

public class NodeResult {
	private int key;
	private Node.Color color;
	private int index;
	private int parentIndex;// really used?
	
	public NodeResult(int key, Node.Color color, int index, int parentIndex) {
		this.key = key;
		this.color = color;
		this.index = index;
		this.parentIndex = parentIndex;
	}
	
	public NodeResult(int key, Node.Color color, int index) {
		this.key = key;
		this.color = color;
		this.index = index;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Node.Color getColor() {
		return color;
	}
	public void setColor(Node.Color color) {
		this.color = color;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getParentIndex() {
		return parentIndex;
	}
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}
	
	public String toString() {
		return key + " " + color + " " + index + " " + parentIndex;
	} 

}