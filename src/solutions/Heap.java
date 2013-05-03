package solutions;

import java.util.ArrayList;

public class Heap {

	private ArrayList<Node> nodes;
	private Type type;
	
	private enum Type { MAX, MIN };
	public static final Type MIN = Type.MIN;
	public static final Type MAX = Type.MAX;
	
	public Heap(int id, int value, Type type){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(new Node(id, value));
		
		this.type = type;
	}
	
	public Heap(Node node, Type type){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(node);
		
		this.type = type;
	}
	
	public Node getRoot(){
		return nodes.get(0);
	}
	
	public Node getLast(){
		return nodes.get(nodes.size());
	}
	
	private void removeLast(){
		nodes.remove(nodes.size());
	}
	
	public void setRoot(Node node){
		nodes.set(0, node);
	}
	
	public Node getParent(int index){
		return nodes.get(index/2);
	}
	
	public void swap(Node node, Node parent){
		int indexNode = nodes.indexOf(node);
		nodes.set(nodes.indexOf(parent), node);
		nodes.set(indexNode, parent);
	}
	
	private void minClimber(Node node){
		if(getRoot() == node)
			return;
		
		while(getParent(nodes.indexOf(node)).getValue() > node.getValue()){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
	
	private void maxClimber(Node node){
		if(getRoot() == node)
			return;
		
		while(getParent(nodes.indexOf(node)).getValue() < node.getValue()){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
	
	public boolean search(int value){
		for (Node node : nodes) {
			if(node.getValue() == value)
				return true;
		}
		return false;
	}
	
	public void insert(Node node){
		if(search(node.getValue()))
			return;
		
		nodes.add(node);
		
		if(type == Type.MIN)
			minClimber(node);
		else
			maxClimber(node);
	}
	
	public Node extract(){
		Node extracted = getRoot();
		Node last = getLast();
		
		removeLast();
		setRoot(last);
		
		//TODO
		//aplicar max ou min heapfy no vetor de nos
		
		return extracted;
	}
	
	public static void main(String[] args) {

	}

}
