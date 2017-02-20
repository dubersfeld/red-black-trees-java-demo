package com.dub.site.redBlackTrees;


import org.springframework.stereotype.Service;

import com.dub.site.redBlackTrees.Node.Color;

@Service
public class DisplayNodeFactory extends NodeFactory<Geometry> {

	@Override
	public DisplayNode build(int key, Color color) {
		return new DisplayNode(key, color);
	}

	@Override
	public DisplayNode build(int key, Color color, Geometry data, Node<Geometry> left, Node<Geometry> right,
			Node<Geometry> parent) {
		return new DisplayNode(key, color, data, left, right, parent);
	}

	

}

