package com.dub.site.redBlackTrees;


// POJO

public class KeyColor {
	private int key;
	private Node.Color color;
	
	public KeyColor(int key, Node.Color color) {
		this.key = key;
		this.color = color;
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
	
	public String toString() {
		return key + " " + color;
	}
	
}