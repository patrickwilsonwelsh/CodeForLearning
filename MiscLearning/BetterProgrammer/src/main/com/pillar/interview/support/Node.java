package com.pillar.interview.support;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Node parent;
	private int value;
	private List<Node> children;
	
	public Node (Node parent, int value) {
		this.parent = parent;
		this.value = value;
		this.children = new ArrayList<Node> ();
		if (parent != null) {parent.getChildren ().add (this);}
	}

	public Node getParent () {return parent;}
	
	public int getValue() {return value;}

	public List<Node> getChildren() {return children;}
	
	@Override
	public String toString () {return Integer.toString (getValue ());}
}
