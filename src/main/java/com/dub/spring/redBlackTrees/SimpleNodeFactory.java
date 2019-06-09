package com.dub.spring.redBlackTrees;


import org.springframework.stereotype.Service;

import com.dub.spring.redBlackTrees.Node.Color;

@Service
public class SimpleNodeFactory extends NodeFactory<String> {

	@Override
	public SimpleNode build(int key, Color color) {
		return new SimpleNode(key, color);
	}

	@Override
	public SimpleNode build(int key, Color color, String data, Node<String> left, Node<String> right,
			Node<String> parent) {
		return new SimpleNode(key, color, data, left, right, parent);
	}

}

