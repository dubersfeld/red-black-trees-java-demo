package com.dub.site.redBlackTrees;

import java.util.ArrayList;
import java.util.List;

public class TreeResponse {
	
	private Type type;
	
	private List<List<NodeResult>> treeArray;
	
	public TreeResponse() {
		treeArray = new ArrayList<List<NodeResult>>();
	}
	
	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public List<List<NodeResult>> getTreeArray() {
		return treeArray;
	}


	public void setTreeArray(List<List<NodeResult>> treeArray) {
		this.treeArray = treeArray;
	}

	
	
	public static enum Type {
		
		OK, FOUND, NOT_FOUND, FORBIDDEN, NODE_PRESENT, CREATED, ERROR
		
	}
}
