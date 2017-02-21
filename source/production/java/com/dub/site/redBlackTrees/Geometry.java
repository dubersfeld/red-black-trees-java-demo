package com.dub.site.redBlackTrees;

import java.io.Serializable;

// POJO

public class Geometry implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depth;
	private int index;
	
	public Geometry() {
	}
	
	public Geometry(Geometry source) {
		this.depth = source.depth;
		this.index = source.index;
	}
	
	

	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
