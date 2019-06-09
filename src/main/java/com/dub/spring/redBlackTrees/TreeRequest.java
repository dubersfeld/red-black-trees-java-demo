package com.dub.spring.redBlackTrees;

import java.util.List;

public class TreeRequest {
	
	private Type type;
		
	private List<Integer> keys;// used for initialization
	
	private int key;
	
	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public List<Integer> getKeys() {
		return keys;
	}


	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}
	
	
	public int getKey() {
		return key;
	}



	public void setKey(int key) {
		this.key = key;
	}


	public static enum Type {
		
		INIT, INSERT, SEARCH, DELETE
		
	}
}